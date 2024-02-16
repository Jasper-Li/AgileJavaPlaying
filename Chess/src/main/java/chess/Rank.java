package chess;

import pieces.Color;
import pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static chess.Board.GRIDS_COUNT_PER_LINE;

public class Rank extends Pieces {
    public static final String BLANK_REPRESENTATION = "........";

    public Rank() {
        super();
    }

    public Rank(Color color, Arrangement arrangement) {
        for(var name : arrangement.names()) {
            pieces.add(new Piece(color, name));
            if(pieces.size() > GRIDS_COUNT_PER_LINE) break;
        }
    }
    public void set(Color color, Arrangement arrangement) {
        int count = 0;
        for(var name : arrangement.names()) {
            final var piece = new Piece(color, name);
            if(pieces.size() == GRIDS_COUNT_PER_LINE){
                pieces.set(count, piece);
            } else {
                pieces.add(piece);
            }
            ++count;
            if(count > GRIDS_COUNT_PER_LINE) break;
        }
    }

    public Rank(String representation) {
        super(representation);
    }

    static Rank createBlankRank() {
        return new Rank(BLANK_REPRESENTATION);
    }
    public void put(ColumnIndex column, Piece piece) {
        pieces.set(column.getInternalIndex(), piece);
    }
    public Piece get(ColumnIndex columnIndex){
        return this.pieces.get(columnIndex.getInternalIndex());
    }
}