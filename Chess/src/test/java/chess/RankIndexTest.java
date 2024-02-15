package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static chess.RankIndex.*;

class RankIndexTest {
    @Test
    void increment(){
        record Check(RankIndex start, RankIndex after) {};
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
            assertEquals(check.after, check.start.increment());
        }
    }
    @Test
    void decrement(){
        record Check(RankIndex start, RankIndex after) {};
        Check[] checks = {
                new Check(R1, INVALID),
                new Check(R2, R1),
                new Check(R3, R2),
                new Check(R4, R3),
                new Check(R5, R4),
                new Check(R6, R5),
                new Check(R7, R6),
                new Check(R8, R7),
                new Check(INVALID, INVALID),
        };
        for(final var check : checks) {
            assertEquals(check.after, check.start.decrement());
        }
    }

}