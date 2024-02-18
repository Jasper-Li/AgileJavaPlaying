package chess;

import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.Pawn;

import java.util.Map;

import static pieces.Color.*;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest extends PiecesTest {
    @Override
    protected Pieces createPieces() {
        return new Column();
    }

    @Override
    protected Pieces createPieces(String representation) {
        return new Column(representation);
    }

    @Test
    void getTypeCount() {
        final var column = new Column("......P.");
        final Map<String, Integer> onePawn = Map.of(
            Pawn.class.getName(), 1
        );
        final var emptyPieces = Map.of();
        assertEquals(onePawn, column.getTypeCount(BLACK));
        assertEquals(emptyPieces, column.getTypeCount(WHITE));
    }
    @Test
    void testGetTypeCount() {
        record Check(String column, Map<String, Integer>black, Map<String, Integer> white){}
        Check[] checks = {
            new Check(
                "rnbqkbnr",
                Map.of(),
                Map.of(
                    "pieces.Rook", 2,
                    "pieces.Knight", 2,
                    "pieces.Bishop", 2,
                    "pieces.King", 1,
                    "pieces.Queen", 1
                )
            ),
            new Check(
                "RNBQKBNR",
                Map.of(
                    "pieces.Rook", 2,
                    "pieces.Knight", 2,
                    "pieces.Bishop", 2,
                    "pieces.King", 1,
                    "pieces.Queen", 1
                ),
                Map.of()
            ),
            new Check(
                "PPP.ppp.",
                Map.of(
                    "pieces.Pawn", 3),
                Map.of(
                    "pieces.Pawn", 3)
            )
        };
        for(final var check : checks){
            final var column = new Column(check.column);
            assertEquals(check.white, column.getTypeCount(WHITE));
            assertEquals(check.black, column.getTypeCount(BLACK));
        }
    }

    record PieceStrength(Integer index, Double strength){
        PieceStrength() {
            this(0, 0.0);
        }
    }
    record ColorStrength(double strengthAll, PieceStrength[] pieceStrengths){
        ColorStrength() {
            this(0.0, new PieceStrength[]{});
        }
    }

    @Test
    void getStrength() {
        //Map
        record Check(String representation, ColorStrength black, ColorStrength white){}
        Check[] checks = {
            new Check("......p.",
                new ColorStrength(),
                new ColorStrength(
                    1.0,
                    new PieceStrength[]{
                        new PieceStrength(6, 1.0),
                    }
                )
            ),
            new Check("......P.",
                new ColorStrength(
                    1.0,
                    new PieceStrength[]{
                        new PieceStrength(6, 1.0),
                    }
                ),
                new ColorStrength()
            ),
            new Check(".....pp.",
                new ColorStrength(),
                new ColorStrength(
                    1.0,
                    new PieceStrength[]{
                        new PieceStrength(5, 0.5),
                        new PieceStrength(6, 0.5),
                    }
                )
            ),
            new Check(".....PP.",
                new ColorStrength(
                    1.0,
                    new PieceStrength[]{
                        new PieceStrength(5, 0.5),
                        new PieceStrength(6, 0.5),
                    }
                ),
                new ColorStrength()
            ),
        };
        for(final var check : checks){
            var column =  new Column(check.representation);
            checkColorStrength(column, BLACK, check.black);
            checkColorStrength(column, WHITE, check.white);

        }
    }
    private void checkColorStrength(Column column, Color color, ColorStrength colorStrength){
        for(final var pieceStrength : colorStrength.pieceStrengths){
            final var piece =column.get(pieceStrength.index);
            assertEquals(0, piece.getStrength());
        }
        column.calculateStrength(color);
        final var msg = STR."Checking \{column.toString()}";
        assertEquals(colorStrength.strengthAll, column.getStrength(color), msg);
        for(final var pieceStrength : colorStrength.pieceStrengths){
            final var piece =column.get(pieceStrength.index);
            assertEquals(pieceStrength.strength, piece.getStrength());
        }
    }
}