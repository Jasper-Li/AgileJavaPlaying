package chess;

import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.Type;

import java.util.Map;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {
    @Test
    void getPieces() {
        final var column = new Column("......P.");
        final EnumMap<Type, Integer> onePawn = new EnumMap<>(Map.of(
               Type.PAWN, 1
        ));
        final var emptyPieces = new EnumMap<Type, Integer>(Type.class) ;
        assertEquals(onePawn, column.getTypeCount(Color.BLACK));
        assertEquals(emptyPieces, column.getTypeCount(Color.WHITE));

        final var blank = column.getPiece(0);
        assertEquals(".", blank.toString());
        final var blackPawn = column.getPiece(6);
        assertEquals("P", blackPawn.toString());
        assertEquals(0, blackPawn.strength());

        assertEquals(1.0, column.getStrength(onePawn, Color.BLACK));
        assertEquals(1.0, blackPawn.strength());

        assertEquals(1.0, column.getStrength(Color.BLACK));
        assertEquals(0.0, column.getStrength(Color.WHITE));
    }

    @Test
    void getStrength() {
        record Check(String representation, double strengthWhite, double strengtBlack){};
        Check[] checks = {
            new Check("......p.", 1.0, 0.0),
                new Check("......P.", 0.0, 1.0),
        };
        for(final var check : checks){
            var column =  new Column(check.representation);
            assertEquals(check.strengtBlack, column.getStrength(Color.BLACK));
            assertEquals(check.strengthWhite, column.getStrength(Color.WHITE));
        }
    }

    @Test
    void getTypeCount() {
        record Check(String representation, EnumMap<Type, Integer> countWhite, EnumMap<Type, Integer> countBlack){};
        Check[] checks = {
            new Check(
                "...P...p",
                new EnumMap<>(Map.of(Type.PAWN, 1)),
                new EnumMap<>(Map.of(Type.PAWN, 1))
            ),
        };
        for (final var check : checks) {
            var column = new Column(check.representation);
            assertEquals(check.countWhite, column.getTypeCount(Color.WHITE));
            assertEquals(check.countBlack, column.getTypeCount(Color.BLACK));
        }
    }
}