package pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlankPieceTest extends  PieceTest {
    private void isBlankPiece(Piece blank){
        assertTrue(blank.isEmpty());
        assertEquals(Color.NONE, blank.getColor());
        assertEquals('.', blank.toChar());
    }
    @Test
    void create(){
        isBlankPiece(new BlankPiece());
        isBlankPiece(Piece.of(Piece.R_NO_PIECE));
    }
}