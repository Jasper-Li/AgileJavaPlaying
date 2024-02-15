package pieces;

import chess.Board;

import java.util.ArrayList;
import java.util.List;

public class Pieces {
    public static List<Piece> createFrom(String representation) {
        List<Piece> pieces = new ArrayList<>();

        int count = 0;
        for (final var c : representation.split("")) {
            final var first = c.charAt(0);
            if(Character.isWhitespace(first)) continue;
            pieces.add(new Piece(first));
            ++count;
            if (count == Board.COLUMN_COUNT) break;
        }
        return pieces;
    }

}
