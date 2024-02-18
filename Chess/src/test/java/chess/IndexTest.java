package chess;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

abstract class IndexTest {
    record ValidInstanceChecker(Character index, int internalIndex) {
    }

    abstract Index of(Character representation);

    abstract ValidInstanceChecker[] createValidIndex();

    abstract Character[] createInvalidIndex();

    @Test
    void of() {
        for (final var check : createValidIndex()) {
            final var index = of(check.index);
            assertTrue(index.isValid());
            assertEquals(check.internalIndex, index.getInternalIndex());
            assertEquals(check.index.toString(), index.toString());
        }
        for (final var invalidRepresentation : createInvalidIndex()) {
            final var index = of(invalidRepresentation);
            assertFalse(index.isValid());
        }
    }

    abstract Character[] createEquals();

    @Test
    void testEquals() {
        for (final var index : createEquals()) {
            final var a_ = of(index);
            if(a_.isColumnIndex()){
                final var a = (ColumnIndex) a_;
                final var b = (ColumnIndex) of(index);
                assertEquals(a, b);
                assertEquals(b, a);
            } else {
                final var a = (RankIndex) a_;
                final var b = (RankIndex) of(index);
                assertEquals(a, b);
                assertEquals(b, a);

            }
        }
    }

    record ConstChecker(Index index, Character expected) {
    }

    abstract ConstChecker[] createConstChecker();

    @Test
    void constCheck() {
        for (final var check : createConstChecker()) {
            final var index = of(check.expected);
            final var msg = STR."check \{check.index} '\{check.expected}'";
            assertEquals(check.index, index, msg);
            assertEquals(index, check.index, msg);
        }
    }

    record PossibleMoves(Index index, List<Integer> possibleMoves) {
    }

    abstract PossibleMoves[] createPossibleMoves();

    @Test
    void getPossibleMoves() {
        for (final var check : createPossibleMoves()) {
            assertEquals(check.possibleMoves, check.index.getPossibleMoves());
        }
    }

    public record MoveChecker(Index index, Map<Integer, Integer> validMoves) {
    }

    public record InvalidMoveChecker(Index index, Integer[] invalidMoves) {
    }

    abstract MoveChecker[] createMoveChecker();

    abstract InvalidMoveChecker[] createInvalidMoveChecker();

    abstract void move();
    abstract void next();

}