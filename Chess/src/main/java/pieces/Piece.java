package pieces;

import chess.Board;
import chess.Location;

import java.util.Map;
import java.util.Set;

import static chess.Board.GRIDS_COUNT_PER_LINE;
import static java.lang.System.out;
import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

public abstract class Piece implements Comparable<Piece>{
    public static final char R_KING = 'k';
    public static final char R_QUEEN = 'q';
    public static final char R_ROOK = 'r';
    public static final char R_BISHOP = 'b';
    public static final char R_KNIGHT = 'n';
    public static final char R_PAWN = 'p';
    public static final char R_NO_PIECE = '.';

    protected final Color color ;
    protected final Character representation ;
    protected double strength = 0;
    protected Piece(Color color, char representation){
        this.color = color;
        this.representation = representation;
    }

    public static Piece of(Character representation) {
        final var color = Character.isUpperCase(representation) ? BLACK : WHITE;
        return switch (Character.toLowerCase(representation)) {
            case R_KING ->  new King(color);
            case R_QUEEN->  new Queen(color);
            case R_ROOK->  new Rook(color);
            case R_BISHOP->  new Bishop(color);
            case R_KNIGHT ->  new Knight(color);
            case R_PAWN->  new Pawn(color);
            default -> new BlankPiece();
        };
    }
    public static Piece[] of(String representation) {
        Piece[] pieces = new Piece[Board.GRIDS_COUNT_PER_LINE];
        int i = 0;
        for (final var c : representation.split("")) {
            final var first = c.charAt(0);
            if(Character.isWhitespace(first)) continue;
            var piece = Piece.of(first);
            pieces[i] = piece;
            ++i;
            if (i == GRIDS_COUNT_PER_LINE) break;
        }
        if(i!= GRIDS_COUNT_PER_LINE){
            for(; i<GRIDS_COUNT_PER_LINE; ++i){
                pieces[i] = new BlankPiece();
            }
        }
        return pieces;
    }
    public static String getStringForArray(Piece[] pieces){
        StringBuilder buffer = new StringBuilder();
        for (var piece : pieces) {
            buffer.append(piece.toString());
        }
        return buffer.toString();
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Piece other) {
            if(color != other.color) {
//                out.println("color mismatch");
                return false;
            }
            if(representation != other.representation){
//                out.println("representation mismatch");
                return false;
            }
            return true;
        } else {
//            out.println("not this type");
            return false;
        }
    }
    public boolean isBlack() {
        return color == BLACK;
    }
    public boolean isWhite() {
        return color == WHITE;
    }
    public boolean isEmpty() {
        return this.getClass() == BlankPiece.class;
    }
    public boolean isPawn() {
        return this.getClass() == Pawn.class;
    }
    public boolean isKing() {
        return this.getClass() == King.class;
    }
    public boolean isQueen() {
        return this.getClass() == Queen.class;
    }
    public Color getColor() {return color;}
    public String toString() {
        return Character.toString(toChar());
    }
    public char toChar() {
        if (isEmpty()) return '.';
        return color == BLACK ?
            Character.toUpperCase(representation) : representation;
    }

    public double getStrength() {
        return strength;
    }

    public Piece setStrength(double strength) {
        this.strength = strength;
        return this;
    }

    @Override
    public int compareTo(Piece o) {
        return Double.compare(getStrength(), o.getStrength());
    }

    public Set<Location> getPossibleMoves(Location current) {
        return Set.of();
    }

    public double getPoint() {
        if(toPoint == null) {
            initializeTypeToPoint();
        }
        return toPoint.getOrDefault(representation, 0.0);
    }

    private static Map<Character, Double> toPoint = null;
    private static void initializeTypeToPoint(){
        toPoint = Map.of(
            R_QUEEN, 9.0,
            R_ROOK, 5.0,
            R_BISHOP, 3.0,
            R_KNIGHT, 2.5,
            R_PAWN, 1.0
        );
    }
}