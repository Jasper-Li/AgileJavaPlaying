package chess;

import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.Piece;
import pieces.Type;

import static org.junit.jupiter.api.Assertions.*;
import static pieces.Color.*;
import static pieces.Type.*;

class RankTest extends PiecesTest {
    protected Pieces createPieces() {
        return new Rank();
    }
    protected Pieces createPieces(String representation) {
        return new Rank(representation);
    }

    private void checkPieceStaticCount(int black, int white) {
        assertEquals(black, Piece.getBlackPiecesCount());
        assertEquals(white, Piece.getWhitePiecesCount());
    }
    @Test
    void testPieceColorCount() {
        Piece.resetPiecesCount(0, 0);
        checkPieceStaticCount(0, 0);
        final var blank = new Rank();
        assertEquals(0, blank.countValidPieces());
        checkPieceStaticCount(0, 0);

        final var whitePawn = new Piece(WHITE, PAWN);
        checkPieceStaticCount(0, 1);
        final var blackPawn = new Piece(BLACK, PAWN);
        checkPieceStaticCount(1, 1);
    }
    @Test
    void createByArrangement() {
        final var rank = new Rank();
        rank.set(BLACK, new BackRankArrangement());
        assertEquals("RNBQKBNR", rank.toString());
        rank.set(WHITE, new SecondRankArrangement());
        assertEquals("pppppppp", rank.toString());
    }
    @Test
    void createBlankLine() {
        assertEquals("........", Rank.createBlankRank().toString());
    }
}