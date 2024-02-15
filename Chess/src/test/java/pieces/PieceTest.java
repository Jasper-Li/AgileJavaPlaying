package pieces;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    void create() {
        final var blank = new Piece();
        assertTrue(blank.isEmpty());
        assertEquals(Color.NONE, blank.color());
        assertEquals(Type.NO_PIECE, blank.type());
        assertEquals(".", blank.toString());

        record CreateCheck(Type type, char representation){}
        CreateCheck[] checks = {
            new CreateCheck(Type.KING, Type.Representation.R_KING),
            new CreateCheck(Type.QUEEN, Type.Representation.R_QUEEN),
            new CreateCheck(Type.ROOK, Type.Representation.R_ROOK),
            new CreateCheck(Type.BISHOP, Type.Representation.R_BISHOP),
            new CreateCheck(Type.KNIGHT, Type.Representation.R_KNIGHT),
            new CreateCheck(Type.PAWN, Type.Representation.R_PAWN),
        };
        for (final var check : checks){
            verifyCreate(check.type(), check.representation());
        }
    }
    private void verifyCreate(Type type, Character representation){
        final var whitePiece = new Piece(Color.WHITE, type);
        assertTrue(whitePiece.isWhite());
        assertEquals(representation.toString(), whitePiece.toString());
        assertEquals(type, whitePiece.type());
        assertFalse(whitePiece.isEmpty());

        final var blackPiece = new Piece(Color.BLACK, type);
        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.type());
        assertEquals(Character.toUpperCase(representation), blackPiece.toString().charAt(0));
        assertFalse(blackPiece.isEmpty());
    }
    @Test
    void createWithPiecesCount() {
        Piece.resetPiecesCount(0, 0);
        assertEquals(0, Piece.getBlackPiecesCount());
        assertEquals(0, Piece.getWhitePiecesCount());

        final var blackPawn = new Piece(Color.BLACK, Type.PAWN);
        assertEquals(1, Piece.getBlackPiecesCount());

        final var whiteKnight = new Piece(Color.WHITE, Type.KNIGHT);
        assertEquals(1, Piece.getWhitePiecesCount());
    }
    @Test
    void createByRepresentation() {
        record RepresentationCreationCheck(Character repr, Color color, Type type){}
        final RepresentationCreationCheck[] checks = {
                new RepresentationCreationCheck('.', Color.NONE, Type.NO_PIECE),
                new RepresentationCreationCheck('k', Color.WHITE, Type.KING),
                new RepresentationCreationCheck('q', Color.WHITE, Type.QUEEN),
                new RepresentationCreationCheck('r', Color.WHITE, Type.ROOK),
                new RepresentationCreationCheck('b', Color.WHITE, Type.BISHOP),
                new RepresentationCreationCheck('n', Color.WHITE, Type.KNIGHT),
                new RepresentationCreationCheck('p', Color.WHITE, Type.PAWN),

                new RepresentationCreationCheck('K', Color.BLACK, Type.KING),
                new RepresentationCreationCheck('Q', Color.BLACK, Type.QUEEN),
                new RepresentationCreationCheck('R', Color.BLACK, Type.ROOK),
                new RepresentationCreationCheck('B', Color.BLACK, Type.BISHOP),
                new RepresentationCreationCheck('N', Color.BLACK, Type.KNIGHT),
                new RepresentationCreationCheck('P', Color.BLACK, Type.PAWN),
                // invalid
                new RepresentationCreationCheck('Z', Color.NONE, Type.NO_PIECE),
        };
        for (var check : checks) {
            final var pieceExpected = new Piece(check.color(), check.type());
            assertEquals(pieceExpected, new Piece(check.repr));
        }
    }

    @Test
    void compareTo() {
        final var p1 = new Piece().setStrength(1.0);
        final var p2 = new Piece().setStrength(2.0);
        final var status = p1.compareTo(p2);
        assertThat(status, lessThan(0));
        assertThat(p2.compareTo(p1), greaterThan(0));
    }
}