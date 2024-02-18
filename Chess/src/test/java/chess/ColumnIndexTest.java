package chess;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static chess.ColumnIndex.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColumnIndexTest extends IndexTest {
    @Override
    Index of(Character representation) {
        return ColumnIndex.of(representation);
    }

    @Override
    ValidInstanceChecker[] createValidIndex() {
        return new ValidInstanceChecker[] {
            new ValidInstanceChecker('a', 0),
            new ValidInstanceChecker('b', 1),
            new ValidInstanceChecker('c', 2),
            new ValidInstanceChecker('d', 3),
            new ValidInstanceChecker('e', 4),
            new ValidInstanceChecker('f', 5),
            new ValidInstanceChecker('g', 6),
            new ValidInstanceChecker('h', 7),
        };
    }

    @Override
    Character[] createInvalidIndex() {
        return new Character[]{
            '1',  '.', 'A', 'i'
        };
    }

    @Override
    Character[] createEquals() {
        return new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    }

    @Override
    ConstChecker[] createConstChecker() {
        return new ConstChecker[]{
            new ConstChecker(A, 'a'),
            new ConstChecker(B, 'b'),
            new ConstChecker(C, 'c'),
            new ConstChecker(D, 'd'),
            new ConstChecker(E, 'e'),
            new ConstChecker(F, 'f'),
            new ConstChecker(G, 'g'),
            new ConstChecker(H, 'h'),
        };
    }

    @Override
    PossibleMoves[] createPossibleMoves() {
        return new PossibleMoves[]{
            new PossibleMoves(A, List.of(1, 2, 3, 4, 5, 6, 7)),
            new PossibleMoves(B, List.of(-1, 1, 2, 3, 4, 5, 6)),
            new PossibleMoves(C, List.of(-2, -1, 1, 2, 3, 4, 5)),
            new PossibleMoves(D, List.of(-3, -2, -1, 1, 2, 3, 4)),
            new PossibleMoves(E, List.of(-4, -3, -2, -1, 1, 2, 3)),
            new PossibleMoves(F, List.of(-5, -4, -3, -2, -1, 1, 2)),
            new PossibleMoves(G, List.of(-6, -5, -4, -3, -2, -1, 1)),
            new PossibleMoves(H, List.of(-7, -6, -5, -4, -3, -2, -1)),
        };
    }

    @Override
    MoveChecker[] createMoveChecker() {
        return new MoveChecker[]{
            new MoveChecker(A, Map.of(
                0, 0,
                1, 1,
                2, 2,
                3, 3,
                4, 4,
                5, 5,
                6, 6,
                7, 7
                )),
        };
    }

    @Override
    InvalidMoveChecker[] createInvalidMoveChecker() {
        return new InvalidMoveChecker[]{
            new InvalidMoveChecker(A, new Integer[]{
                -1, 8, 9
            }),
        };
    }

    @Override
    @Test
    void move() {
        for (final var moveChecker : createMoveChecker()) {
            moveChecker.validMoves().forEach((step, after) -> {
                final var optionalIndex = ((ColumnIndex)(moveChecker.index())).move(step);
                assertTrue(optionalIndex.isPresent());
                assertEquals(after, optionalIndex.get().getInternalIndex());
            });
        }

        for (final var invalidMoveChecker : createInvalidMoveChecker()) {
            final var index =(ColumnIndex) invalidMoveChecker.index();
            for (final var step : invalidMoveChecker.invalidMoves()) {
                assertTrue(index.move(step).isEmpty(),
                    STR."Check \{index} at step \{step}");
            }
        }
    }

    @Override
    @Test
    void next() {
        record Check(ColumnIndex start, ColumnIndex after) {}
        Check[] checks = {
            new Check(A, B),
            new Check(B, C),
            new Check(C, D),
            new Check(D, E),
            new Check(E, F),
            new Check(F, G),
            new Check(G, H),
            new Check(H, INVALID),
            new Check(INVALID, INVALID),
        };
        for(final var check : checks) {
            assertEquals(check.after, check.start.next());
        }
    }

    @Test
    void testEquals2(){
        final var a = new ColumnIndex(1);
        final var b = new ColumnIndex(2);
        assertEquals(B, a);
        assertEquals(Set.of(B), Set.of(a));
        final var sets = Set.of(a, b);
        assertEquals(2, sets.size());
        assertEquals(sets, Set.of(B, C));
    }
}