package chess;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static chess.RankIndex.*;

class RankIndexTest extends IndexTest{
    @Override
    Index of(Character representation) {
        return RankIndex.of(representation);
    }

    @Override
    ValidInstanceChecker[] createValidIndex() {
        return new ValidInstanceChecker[]{
            new ValidInstanceChecker('1', 0),
            new ValidInstanceChecker('2', 1),
            new ValidInstanceChecker('3', 2),
            new ValidInstanceChecker('4', 3),
            new ValidInstanceChecker('5', 4),
            new ValidInstanceChecker('6', 5),
            new ValidInstanceChecker('7', 6),
            new ValidInstanceChecker('8', 7),
        };
    }

    @Override
    Character[] createInvalidIndex() {
        return new Character[]{
            '0', '9', 'a'
        };
    }

    @Override
    Character[] createEquals() {
        return new Character[]{
            '1','2','3','4','5','6','7','8'
        };
    }

    @Override
    ConstChecker[] createConstChecker() {
        return new ConstChecker[]{
            new ConstChecker(R1, '1'),
            new ConstChecker(R2, '2'),
            new ConstChecker(R3, '3'),
            new ConstChecker(R4, '4'),
            new ConstChecker(R5, '5'),
            new ConstChecker(R6, '6'),
            new ConstChecker(R7, '7'),
            new ConstChecker(R8, '8'),
        };
    }

    @Override
    PossibleMoves[] createPossibleMoves() {
        return new PossibleMoves[]{
            new PossibleMoves(R1, List.of(1, 2, 3, 4, 5, 6, 7)),
            new PossibleMoves(R2, List.of(-1, 1, 2, 3, 4, 5, 6)),
            new PossibleMoves(R3, List.of(-2, -1, 1, 2, 3, 4, 5)),
            new PossibleMoves(R4, List.of(-3, -2, -1, 1, 2, 3, 4)),
            new PossibleMoves(R5, List.of(-4, -3, -2, -1, 1, 2, 3)),
            new PossibleMoves(R6, List.of(-5, -4, -3, -2, -1, 1, 2)),
            new PossibleMoves(R7, List.of(-6, -5, -4, -3, -2, -1, 1)),
            new PossibleMoves(R8, List.of(-7, -6, -5, -4, -3, -2, -1)),
        };
    }

    @Override
    MoveChecker[] createMoveChecker() {
        return new MoveChecker[]{
            new MoveChecker(R1, Map.of(
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
            new InvalidMoveChecker(R1, new Integer[]{
                -1, 8, 9
            }),
        };
    }
    @Test
    void next(){
        record Check(RankIndex start, RankIndex after) {}
        Check[] checks = {
            new Check(R1, R2),
            new Check(R2, R3),
            new Check(R3, R4),
            new Check(R4, R5),
            new Check(R5, R6),
            new Check(R6, R7),
            new Check(R7, R8),
            new Check(R8, INVALID),
            new Check(INVALID, INVALID),
        };
        for(final var check : checks) {
            assertEquals(check.after, check.start.next());
        }
    }

    @Override
    @Test
    void move() {
        for (final var moveChecker : createMoveChecker()) {
            moveChecker.validMoves().forEach((step, after) -> {
                final var optionalIndex = ((RankIndex)(moveChecker.index())).move(step);
                assertTrue(optionalIndex.isPresent());
                assertEquals(after, optionalIndex.get().getInternalIndex());
            });
        }

        for (final var invalidMoveChecker : createInvalidMoveChecker()) {
            final var index =(RankIndex) invalidMoveChecker.index();
            for (final var step : invalidMoveChecker.invalidMoves()) {
                assertTrue(index.move(step).isEmpty(),
                    STR."Check \{index} at step \{step}");
            }
        }
    }
}