package pieces;

import org.junit.jupiter.api.Test;

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
}
