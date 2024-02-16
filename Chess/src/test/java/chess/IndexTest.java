package chess;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static chess.ColumnIndex.*;
import static chess.RankIndex.*;

class IndexTest {

    @Test
    void of() {
        final int[] validIndexes = {0,1,2,3,4,5,6,7};
        for(final int validIndex : validIndexes){
            final var index = Index.of(validIndex);
            assertTrue(index.isPresent());
            assertEquals(validIndex, index.get().value());
        }
        final int[] invalidIndexes = {-5, -1, 8, 10};
        for(final var invalidIndex : invalidIndexes){
            final var index = Index.of(invalidIndex);
            assertTrue(index.isEmpty());
        }
    }

    @Test
    void move() {
        final var start = Index.of(4).get();
        Map<Integer, Integer> validMoves = Map.of(
            -4, 0,
            -3, 1,
            -2, 2,
            -1, 3,
            1, 5,
            2, 6,
            3, 7
        );
        validMoves.forEach((step, after) ->{
           final var optionalIndex = start.move(step);
           assertTrue(optionalIndex.isPresent());
           assertEquals(after, optionalIndex.get().value());
        });
        int[] invalidSteps = {-5, 0, 4};
        for(final var step: invalidSteps) {
            final var optionalIndex = start.move(step);
            assertTrue(optionalIndex.isEmpty());
        }
    }

    @Test
    void toColumnRankIndex() {
        Index index = Index.of(0).get();
        assertEquals(A, index.toColumnIndex());
        assertEquals(R1, index.toRankIndex());
    }

    @Test
    void testToString() {
        record Check(int index, String string){}
        Check[] checks = {
            new Check(0, "0"),
            new Check(1, "1"),
            new Check(2, "2"),
            new Check(3, "3"),
            new Check(4, "4"),
            new Check(5, "5"),
            new Check(6, "6"),
            new Check(7, "7"),
        };
        for(final var check : checks) {
            assertEquals(check.string, Index.of(check.index).get().toString());
        }
    }
}