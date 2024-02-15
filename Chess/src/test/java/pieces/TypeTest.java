package pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeTest {
    @Test
    void create() {
        record Check(char representation, Type type){};
        final Check[] checks = {
            new Check('k', Type.KING),
            new Check('q', Type.QUEEN),
            new Check('r', Type.ROOK),
            new Check('b', Type.BISHOP),
            new Check('n', Type.KNIGHT),
            new Check('p', Type.PAWN),
            new Check('K', Type.KING),
            new Check('Q', Type.QUEEN),
            new Check('R', Type.ROOK),
            new Check('B', Type.BISHOP),
            new Check('N', Type.KNIGHT),
            new Check('P', Type.PAWN),
        };
        for (var check : checks) { //var check is record, no need final.
            assertEquals(check.type(), Type.valueOf(check.representation()));
        }

    }

    @Test
    void getPoint() {
        record Check(Type type, Double point){};
        final Check[] checks = {
            new Check(Type.QUEEN, 9.0),
            new Check(Type.ROOK, 5.0),
            new Check(Type.BISHOP, 3.0),
            new Check(Type.KNIGHT, 2.5),
            new Check(Type.PAWN, 1.0),
        };
        for (var check : checks) {
            assertEquals(check.point, check.type.getPoint());
        }
    }
}