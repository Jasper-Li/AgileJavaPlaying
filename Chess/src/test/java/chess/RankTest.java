package chess;

import org.junit.jupiter.api.Test;
import pieces.Pawn;

import static org.junit.jupiter.api.Assertions.*;
import static pieces.Color.*;

class RankTest extends PiecesTest {
    protected Pieces createPieces() {
        return new Rank();
    }
    protected Pieces createPieces(String representation) {
        return new Rank(representation);
    }

    @Test
    void testPieceColorCount() {
        final var rank = new Rank();
        assertEquals(0, rank.countValidPieces());

        final var whitePawn = new Pawn(WHITE);
        final var blackPawn = new Pawn(BLACK);
        rank.put(ColumnIndex.A, whitePawn);
        assertEquals(1, rank.countValidPieces());
        rank.put(ColumnIndex.B, blackPawn);
        assertEquals(2, rank.countValidPieces());
    }
    @Test
    void createBlankLine() {
        assertEquals("........", Rank.createBlankRank().toString());
    }
}