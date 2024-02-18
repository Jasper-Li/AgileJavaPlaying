package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Color;

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
    private Game game;
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void initialize() {
        game.initialize();
        var board = game.getBoard();
        assertEquals(new Board(initialBoard), board);
    }

    @Test
    void getStrength() {
        game.setBoard(new Board(BoardTest.board_5_4));
        final var board = game.getBoard();

        final var blackKing = board.get(new Location("b8"));
        assertEquals(0.0, blackKing.getStrength());
        final var blackBishop = board.get(new Location("d7"));
        assertEquals(0.0,blackBishop.getStrength());

        final var blackPoints = 20.0;
        final var whitePoints = 19.5;
        assertEquals(whitePoints, game.getStrength(Color.WHITE));
        assertEquals(blackPoints, game.getStrength(Color.BLACK));

        assertEquals(0.0, blackKing.getStrength());
        assertEquals(3.0,blackBishop.getStrength());
    }
}
