package pieces;

import java.util.Map;
import static pieces.Type.Representation.*;

public enum Type {
    KING(R_KING),
    QUEEN(R_QUEEN),
    ROOK(R_ROOK),
    BISHOP(R_BISHOP),
    PAWN(R_PAWN),
    KNIGHT(R_KNIGHT),
    NO_PIECE(R_NO_PIECE);

    public static abstract class Representation {
        public static final char R_KING = 'k';
        public static final char R_QUEEN = 'q';
        public static final char R_ROOK = 'r';
        public static final char R_BISHOP = 'b';
        public static final char R_KNIGHT = 'n';
        public static final char R_PAWN = 'p';
        public static final char R_NO_PIECE = '.';
    }
    private static Map<Type, Double> typeToPoint = null;
    private static Map<Character, Type> representationToType = null;
    private final char representation;
    Type(char representation){
        this.representation = representation;
    }

    public static Type valueOf(char representation) {
        final var lowerCase = Character.toLowerCase(representation);
        if(representationToType == null){
            initRepresentationToType();
        }
        return representationToType.getOrDefault(lowerCase, NO_PIECE);
    }
    public char getRepresentation() {
        return representation;
    }
    public double getPoint() {
        if(typeToPoint == null) {
            initializeTypeToPoint();
        }
        return typeToPoint.getOrDefault(this, 0.0);
    }

    private static void initializeTypeToPoint(){
        typeToPoint = Map.of(
                QUEEN, 9.0,
                ROOK, 5.0,
                BISHOP, 3.0,
                KNIGHT, 2.5,
                PAWN, 1.0
        );
    }
    private static void initRepresentationToType() {
        representationToType = Map.of(
                R_KING, KING,
                R_QUEEN, QUEEN,
                R_ROOK, ROOK,
                R_BISHOP, BISHOP,
                R_KNIGHT, KNIGHT,
                R_PAWN, PAWN
        );
    }
}

