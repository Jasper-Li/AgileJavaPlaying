package chess;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

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
            final var msg = STR."""
                check "\{check.representation}", \{check.column} \{check.rank} """;
            assertEquals(check.column, locationExpect.column());
            assertEquals(check.rank, locationExpect.rank(), msg);
            assertEquals(locationExpect, new Location(check.representation), msg);
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

    @Test
    void move() {
        record PossibleMove(int columnStep, int rankStep, String after){}
        final var current = new Location("e4");
        final PossibleMove[] possibleMoves = {
            new PossibleMove(0, 1, "e5"),
            new PossibleMove(0, -1, "e3"),
            new PossibleMove(1, 1, "f5"),
            new PossibleMove(1, 0, "f4"),
            new PossibleMove(1, -1, "f3"),
            new PossibleMove(-1, 1, "d5"),
            new PossibleMove(-1, 0, "d4"),
            new PossibleMove(-1, -1, "d3"),
        };
        for (final var possibleMove : possibleMoves){
            final var optionalAfter = current.move(possibleMove.columnStep, possibleMove.rankStep);
            final var msg = STR."current: \{current} \{possibleMove}";
            assertTrue(optionalAfter.isPresent(), msg);
            assertEquals(new Location(possibleMove.after), optionalAfter.get(), msg);
        }
        record InvalidMove(int columnStep, int rankStep){}
        InvalidMove[] invalidMoves = {
            new InvalidMove(0, 5),
            new InvalidMove(0, -4),
            new InvalidMove(4, 0),
            new InvalidMove(-5, 0),
            new InvalidMove(4, 5),
            new InvalidMove(4, -4),
            new InvalidMove(-5, 5),
            new InvalidMove(-5, -4),
        };
        for(final var invalidMove : invalidMoves) {
            final var optionalAfter = current.move(invalidMove.columnStep, invalidMove.rankStep);
        }
    }

    @Test
    void getDiagonalSteps() {
        record Check(String location, Location.DiagonalSteps diagonalSteps){}
        final Check[] checks = {
            new Check("e4", new Location.DiagonalSteps(
                OptionalInt.of(4),
                OptionalInt.of(3),
                OptionalInt.of(3),
                OptionalInt.of(3)
            )),
            new Check("a1", new Location.DiagonalSteps(
                OptionalInt.empty(),
                OptionalInt.empty(),
                OptionalInt.of(7),
                OptionalInt.empty()
            )),
            new Check("h8", new Location.DiagonalSteps(
                OptionalInt.empty(),
                OptionalInt.of(7),
                OptionalInt.empty(),
                OptionalInt.empty()
            )),
        };
        for(final var check : checks) {
            assertEquals(check.diagonalSteps, new Location(check.location).getDiagonalSteps());
        }

    }
}