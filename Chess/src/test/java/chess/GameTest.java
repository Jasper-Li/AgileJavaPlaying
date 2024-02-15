package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void initialize() {
        var game = new Game();
        game.initialize();
        String boardExpected = """
            R N B Q K B N R 8
            P P P P P P P P 7
            . . . . . . . . 6
            . . . . . . . . 5
            . . . . . . . . 4
            . . . . . . . . 3
            p p p p p p p p 2
            r n b q k b n r 1
            a b c d e f g h""";
        var board = game.getBoard();
        assertEquals(new Board(boardExpected), board);

    }
}
