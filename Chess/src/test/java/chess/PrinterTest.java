package chess;

import chess.Board;
import chess.Printer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {
    @Test
    void printBoard() {
        final String boardRepresentation = getBoardRepresentation();
        final var board = new Board().initialize();
        final String boardStringByStringBuilder = Printer.print(board);
        assertEquals(boardRepresentation, boardStringByStringBuilder);
        final String boardStringByStringConcatenation = Printer.printByStringConcatenation(board);
        assertEquals(boardRepresentation, boardStringByStringConcatenation);
    }

    private static String getBoardRepresentation() {
        final String boardLookExpectWin = """
                RNBQKBNR\r
                PPPPPPPP\r
                ........\r
                ........\r
                ........\r
                ........\r
                pppppppp\r
                rnbqkbnr\r
                """;
        final String boardLookExpectOther = """
                RNBQKBNR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rnbqkbnr
                """;
        final String boardLookExpect = util.System.isWindows() ? boardLookExpectWin : boardLookExpectOther;
        return boardLookExpect;
    }
}
