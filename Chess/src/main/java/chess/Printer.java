package chess;

import pieces.Piece;
import util.StringUtil;

import java.util.List;

public class Printer {
    public static String print(Board board) {
        return board.toString();
    }

    public static String printByStringConcatenation(Board board) {
        return board.toString();
    }

    public static void print(final List<Piece> pieces){
        for (final var piece : pieces) {
            System.out.println(STR."\{piece} strength: \{piece.getStrength()}");
        }
    }
}
