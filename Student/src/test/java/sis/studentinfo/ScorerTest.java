package sis.studentinfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScorerTest {
    @Test
    void scorer() {
        var scorer = new Scorer();
        assertEquals(75, scorer.scorer("75"));
        try {
            scorer.scorer("abc");
            fail("expected exception.");
        } catch (NumberFormatException e) {
        }
    }
}