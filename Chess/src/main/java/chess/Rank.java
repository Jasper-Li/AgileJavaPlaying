package chess;

import pieces.Piece;

import java.util.List;

public class Rank extends Pieces {

    public Rank() {
        super();
    }
    Rank(List<Piece> pieces) {
        super(pieces);
    }

    public Rank(String representation) {
        super(representation);
    }

    static Rank createBlankRank() {
        return new Rank(BLANK_REPRESENTATION);
    }
    public void put(ColumnIndex column, Piece piece) {
        put(column.getInternalIndex(), piece);
    }
    public Piece get(ColumnIndex columnIndex){
        return this.pieces.get(columnIndex.getInternalIndex());
    }
}