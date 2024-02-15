package chess;

import pieces.Piece;
import util.StringUtil;

import java.util.List;

public class Printer {
    public static String print(Board board) {
        return board.toString();
    }

    public static String printByStringConcatenation(Board board) {
        if(board.countRanks() != Board.RANK_COUNT) return "";
        String result = new String();
        for(int i = Board.RANK_COUNT; i > 0; --i) {
            var rankIndex = RankIndex.of((char)('0' + i));
            var rank = board.getRank(rankIndex).toString();
            var line = StringUtil.appendNewLine(rank);
            result += line;
        }
        return result;
    }

    public static void print(final List<Piece> pieces){
        for (final var piece : pieces) {
            System.out.println(STR."\{piece} strength: \{piece.strength()}");
        }
    }
}
