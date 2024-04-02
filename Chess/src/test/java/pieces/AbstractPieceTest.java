package pieces;

import chess.Board;
import chess.Location;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractPieceTest {
    record CreateByNew(Piece piece, Color color, char representation){}
    protected abstract CreateByNew[] createByNew();

    @Test
    void createByNewTest() {
        final var pieceCheckers = createByNew();
        for(int i = 0; i < pieceCheckers.length; ++i){
            final var pieceChecker = pieceCheckers[i];
            final var piece = pieceChecker.piece;
            final var msg = STR."at index \{i}";
            assertFalse(piece.isEmpty(), msg);
            assertEquals(pieceChecker.color, piece.getColor(), msg);
            assertEquals(pieceChecker.representation, piece.toChar(), msg);
        }
    }

    record CreateByOf(char representation, Class pieceClass, Color color){}
    protected abstract CreateByOf[] createByOf();
    @Test
    void createByOfTest() {
        for(final var check : createByOf()) {
            final var piece = Piece.of(check.representation);
            assertEquals(check.pieceClass, piece.getClass());
            assertEquals(check.color, piece.getColor());
            assertEquals(check.representation, piece.toChar());
        }
    }

    protected abstract Piece createPiece();
    @Test
    void compareTo() {
        final var p1 = createPiece().setStrength(1.0);
        final var p2 = createPiece().setStrength(2.0);
        final var status = p1.compareTo(p2);
        assertThat(status, lessThan(0));
        assertThat(p2.compareTo(p1), greaterThan(0));
    }

    record CurrentNext(String current, String next){}
    protected record PossibleMoveTest(char piece, List<CurrentNext> currentNexts){
        public PossibleMoveTest() {
            this('.', List.of());
        }
    }

/**
 * Possible format.
            new CurrentNext(
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1""",
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1"""
             )
*/
    protected abstract PossibleMoveTest createPossibleMoveTest();
    @Test
    void getPossibleMoves() {
        final PossibleMoveTest possibleMoveTest = createPossibleMoveTest();
        final var piece = Piece.of(possibleMoveTest.piece);
        for (final var currentNext : possibleMoveTest.currentNexts) {
            final var currentBoard = new Board(currentNext.current);
            final var currentLocations = currentBoard.getLocations(piece);
            assertEquals(1, currentLocations.size());
            final Location currentLocation = currentLocations.toArray(new Location[0])[0];
            final var result = piece.getPossibleMoves(currentLocation);

            final var nextLocationsExpected = new Board(currentNext.next).getLocations(piece);
            assertEquals(nextLocationsExpected, result);
        }
    }
}
