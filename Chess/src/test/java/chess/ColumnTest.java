package chess;

import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.Type;

import java.util.Map;
import java.util.EnumMap;

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
        final var emptyPieces = new EnumMap<Type, Integer>(Type.class) ;
        assertEquals(onePawn, column.getTypeCount(Color.BLACK));
        assertEquals(emptyPieces, column.getTypeCount(Color.WHITE));

        // TODO: move to getStrength
        final var blackPawn = column.get(6);
        assertEquals(0, blackPawn.strength()); //initial strength is zero.

        assertEquals(1.0, column.getStrength(onePawn, Color.BLACK)); // static method
        assertEquals(1.0, blackPawn.strength()); // save strength to black pawn.

        assertEquals(1.0, column.getStrength(Color.BLACK)); // instance method
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
//
//    @Test
//    void getTypeCount() {
//        record Check(String representation, EnumMap<Type, Integer> countWhite, EnumMap<Type, Integer> countBlack){};
//        Check[] checks = {
//            new Check(
//                "...P...p",
//                new EnumMap<>(Map.of(Type.PAWN, 1)),
//                new EnumMap<>(Map.of(Type.PAWN, 1))
//            ),
//        };
//        for (final var check : checks) {
//            var column = new Column(check.representation);
//            assertEquals(check.countWhite, column.getTypeCount(Color.WHITE));
//            assertEquals(check.countBlack, column.getTypeCount(Color.BLACK));
//        }
//    }
}