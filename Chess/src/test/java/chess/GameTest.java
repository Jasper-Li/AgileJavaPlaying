package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    public static final  String initialBoard = """
            R N B Q K B N R 8
            P P P P P P P P 7
            . . . . . . . . 6
            . . . . . . . . 5
            . . . . . . . . 4
            . . . . . . . . 3
            p p p p p p p p 2
            r n b q k b n r 1
            a b c d e f g h""";

    @Test
    void initialize() {
        var game = new Game();
        game.initialize();
        var board = game.getBoard();
        assertEquals(new Board(initialBoard), board);

    }
}
