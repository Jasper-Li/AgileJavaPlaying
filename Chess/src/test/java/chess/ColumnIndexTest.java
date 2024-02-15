package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static chess.ColumnIndex.*;

class ColumnIndexTest {

    @Test
    void testToString() {
        assertEquals("A", ColumnIndex.A.toString());
    }

    @Test
    void increment() {
        record Check(ColumnIndex start, ColumnIndex after) {};
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
            assertEquals(check.after, check.start.increment());
        }
    }
    @Test
    void decrement() {
        record Check(ColumnIndex start, ColumnIndex after) {};
        Check[] checks = {
                new Check(A, INVALID),
                new Check(B, A),
                new Check(C, B),
                new Check(D, C),
                new Check(E, D),
                new Check(F, E),
                new Check(G, F),
                new Check(H, G),
                new Check(INVALID, INVALID),
        };
        for(final var check : checks) {
            assertEquals(check.after, check.start.decrement());
        }
    }
}