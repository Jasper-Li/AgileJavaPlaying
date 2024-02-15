package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static chess.RankIndex.*;
import static chess.ColumnIndex.*;

class LocationTest {
    @Test
    void create() {
        record LocationCheck(String representation, ColumnIndex column, RankIndex rank){}
        final LocationCheck[] checks = {
            new LocationCheck("a8", A, R8),
            new LocationCheck("a1", A, R1),
            new LocationCheck("h8", H, R8),
            new LocationCheck("e1", E, R1),
        };
        for (final var check : checks) {
            final var locationExpect = new Location(check.column, check.rank);
            assertEquals(check.column, locationExpect.column());
            assertEquals(check.rank, locationExpect.rank());
            assertEquals(locationExpect, new Location(check.representation));
            assertEquals(check.representation, locationExpect.toString());
        }
    }

    @Test
    void isValid() {
        assertTrue(new Location("a8").isValid());
        assertFalse(new Location("a9").isValid());
        assertFalse(new Location("i8").isValid());
        assertFalse(new Location("i9").isValid());
    }
}