package chess;

import pieces.Color;
import pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static chess.Board.GRIDS_COUNT_PER_LINE;

public class Pieces {
    protected final List<Piece> pieces = new ArrayList<>(GRIDS_COUNT_PER_LINE);
    Pieces() {
//        for(int i = 0; i < GRIDS_COUNT_PER_LINE; ++i) {
//            pieces.add(new Piece());
//        }
    }
    Pieces(String representation){
        this();
        set(representation);
    }
    void set(String representation) {
        if(representation.isEmpty()) return;
        int index = 0;
        for (final var c : representation.split("")) {
            final var first = c.charAt(0);
            if(Character.isWhitespace(first)) continue;
            var piece = new Piece(first);
            if(pieces.size() > index) {
                pieces.set(index, piece);
            } else {
                pieces.add(piece);
            }
            ++index;
            if (index == GRIDS_COUNT_PER_LINE) break;
        }
    }

    void put(int index, Piece piece){
        pieces.set(index, piece);
    }
    Piece get(int index){
        return pieces.get(index);
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pieces that){
            return this.pieces.equals(that.pieces);
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
    public int count(Piece piece) {
        int count = 0;
        for (var p: pieces) {
            if (piece.equals(p)){
                ++count;
            }
        }
        return count;
    }
    public Pieces getPieces(Color color) {
        final var pieces = new Pieces();
        for(final var piece : this.pieces){
            if(!piece.isEmpty() && piece.color() == color) {
                pieces.pieces.add(piece);
            }
        }
        return  pieces;
    }
}
