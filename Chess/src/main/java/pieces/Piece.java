package pieces;

public class Piece implements Comparable<Piece>{
    private static int whitePiecesCount = 0;
    private static int blackPiecesCount = 0;
    private final Color color;
    private final Type type;
    private double strength = 0;
    public static void resetPiecesCount(int white, int black) {
        whitePiecesCount = white;
        blackPiecesCount = black;
    }
    public static int getWhitePiecesCount() {
        return whitePiecesCount;
    }
    public static int getBlackPiecesCount() {
        return blackPiecesCount;
    }

    /**
     * blank piece. Type.NoPiece
     */
    public Piece(){
        this(Color.NONE, Type.NO_PIECE);
    }

    public Piece(Character representation) {
        type = Type.valueOf(representation);
        if (type == Type.NO_PIECE){
            color = Color.NONE;
        } else if (Character.isUpperCase(representation)) {
            color = Color.BLACK;
        } else {
            color = Color.WHITE;
        }
        updateStaticWhiteBlackCount();
    }
    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        updateStaticWhiteBlackCount();
    }
    private void updateStaticWhiteBlackCount(){
        switch (color){
            case Color.WHITE -> ++whitePiecesCount;
            case Color.BLACK -> ++blackPiecesCount;
        };
    }
    @Override
    public boolean equals(Object o){
        return o instanceof Piece other &&
            color == other.color && type == other.type;
    }
    public boolean isBlack() {
        return color == Color.BLACK;
    }
    public boolean isWhite() {
        return color == Color.WHITE;
    }
    public boolean isEmpty() {
        return type == Type.NO_PIECE;
    }
    public Color color() {return color;}
    public Type type() {return type;}
    public String toString() {
        return Character.toString(toChar());
    }
    public char toChar() {
        if (type == Type.NO_PIECE) return '.';
        final var name = type.getRepresentation();
        return color == Color.BLACK ?
                Character.toUpperCase(name) : name;
    }

    public double strength() {
        return strength;
    }

    public Piece setStrength(double strength) {
        this.strength = strength;
        return this;
    }

    @Override
    public int compareTo(Piece o) {
        return Double.compare(strength(), o.strength());
    }
}