package chess;

import pieces.Color;
import pieces.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static chess.Board.GRIDS_COUNT_PER_LINE;

public class Pieces implements  Iterable<Piece> {
    public static final String BLANK_REPRESENTATION = "........";
    protected final List<Piece> pieces = new ArrayList<>(GRIDS_COUNT_PER_LINE);
    Pieces() {}
    Pieces(List<Piece> pieces) {
        this.pieces.clear();
        this.pieces.addAll(pieces);
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
            var piece = Piece.of(first);
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
        if(pieces.size() != GRIDS_COUNT_PER_LINE) {
            set(BLANK_REPRESENTATION);
        }
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
        return obj instanceof Pieces that && this.pieces.equals(that.pieces);
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
            if(!piece.isEmpty() && piece.getColor() == color) {
                pieces.pieces.add(piece);
            }
        }
        return  pieces;
    }

    List<Piece> getPieces() {
        return pieces;
    }
    public void addAll(Pieces some) {
        this.pieces.addAll(some.pieces);
    }
    public void add(Piece piece) {
        this.pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }
    public void sort() {
        Comparator<Piece> comparator = (p1, p2) -> Double.compare(p1.getStrength(), p2.getStrength());
        pieces.sort(comparator.reversed());
    }

    @Override
    public Iterator<Piece> iterator() {
        return pieces.iterator();
    }
}
