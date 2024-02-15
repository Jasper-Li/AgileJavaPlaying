package chess;

import pieces.Color;
import pieces.Piece;
import pieces.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private List<Piece> pieces = new ArrayList<Piece>();

    /**
     * blank rank uses blank Piece.
     */
    public Rank() {
        for(int i = 0; i < Board.COLUMN_COUNT; ++i) {
            pieces.add(new Piece());
        }
    }

    public Rank(Color color, Arrangement arrangement) {
        for(var name : arrangement.names()) {
            pieces.add(new Piece(color, name));
            if(pieces.size() > Board.COLUMN_COUNT) break;
        }
    }

    public Rank(String representation) {
        this.pieces  = Pieces.createFrom(representation);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Rank that) {
            return pieces.equals(that.pieces);
        }
        return false;
    }

    public int countValidPieces() {
        int count = 0;
        for (var piece : pieces) {
            if (piece != null && !piece.isEmpty()) {
                ++count;
            }
        }
        return count;
    }

    Piece getPiece(ColumnIndex column ) {
        return pieces.get(column.getInternalIndex());
    }

    public String toString(){
        StringBuilder buffer = new StringBuilder();
        for (var piece : pieces) {
            buffer.append(piece.toString());
        }
        return buffer.toString();
    }
    public String toPrettyString(){
        StringBuilder buffer = new StringBuilder();
        for (var piece : pieces) {
            buffer.append(piece.toString());
            buffer.append(" ");
        }
        return buffer.toString();
    }

    public int count(Piece piece) {
        int count = 0;
        for (var p: pieces) {
            if (piece.equals(p)){
                ++count;
            }
        }
        return count;
    }


    /**
     *
     * @param column, count from 0.
     * @return
     */
    public void placePiece(Piece piece, ColumnIndex column) {
        pieces.set(column.getInternalIndex(), piece);
    }

    public List<Piece> getPieces(Color color) {
        List<Piece> pieces = new ArrayList<>();
        for(final var piece : this.pieces){
            if(!piece.isEmpty() && piece.color() == color) {
                pieces.add(piece);
            }
        }
        return  pieces;
    }
}