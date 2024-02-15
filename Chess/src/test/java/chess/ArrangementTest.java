package chess;

import org.junit.jupiter.api.Test;
import pieces.Type;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementTest {

    @Test
    void names() {
        final List<Type> secondRank = new SecondRankArrangement().names();
        assertEquals(8, secondRank.size());
        for (var name : secondRank) {
            assertEquals(Type.PAWN, name);
        }

        final List<Type> backRank = new BackRankArrangement().names();
        assertEquals(8, backRank.size());
        assertEquals(Type.ROOK, backRank.getFirst());
        assertEquals(Type.ROOK, backRank.getLast());
    }
}