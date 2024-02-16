package chess;

import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.Type;

import java.util.Map;
import java.util.EnumMap;
import static pieces.Type.*;
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
        final EnumMap<Type, Integer> onePawn = new EnumMap<>(Map.of(
            Type.PAWN, 1
        ));
        final var emptyPieces = new EnumMap<Type, Integer>(Type.class);
        assertEquals(onePawn, column.getTypeCount(BLACK));
        assertEquals(emptyPieces, column.getTypeCount(WHITE));
    }
    @Test
    void testGetTypeCount() {
        record Check(String column, EnumMap<Type, Integer>black, EnumMap<Type, Integer> white){};
        Check[] checks = {
            new Check(
                "rnbqkbnr",
                new EnumMap<>(Type.class),
                new EnumMap<>(Map.of(
                    ROOK, 2,
                    KNIGHT, 2,
                    BISHOP, 2,
                    KING, 1,
                    QUEEN, 1
                ))
            ),
            new Check(
                "RNBQKBNR",
                new EnumMap<>(Map.of(
                    ROOK, 2,
                    KNIGHT, 2,
                    BISHOP, 2,
                    KING, 1,
                    QUEEN, 1
                )),
                new EnumMap<>(Type.class)
            ),
            new Check(
                "PPP.ppp.",
                new EnumMap<>(Map.of(
                    PAWN, 3)),
                new EnumMap<>(Map.of(
                    PAWN, 3))
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
    };
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
        assertEquals(colorStrength.strengthAll, column.getStrength(color));
        for(final var pieceStrength : colorStrength.pieceStrengths){
            final var piece =column.get(pieceStrength.index);
            assertEquals(pieceStrength.strength, piece.getStrength());
        }
    }
}