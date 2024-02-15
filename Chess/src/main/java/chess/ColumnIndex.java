package chess;

import java.util.Map;

public enum ColumnIndex {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    INVALID(-1);

    private int internalIndex;
    ColumnIndex(int internalIndex) {
        this.internalIndex = internalIndex;
    }
    boolean isValid() {
        return this != INVALID;
    }
    public int getInternalIndex() {
        return internalIndex;
    }
    public ColumnIndex increment() {
        return switch(this){
            case H, INVALID -> INVALID;
            default -> of((char)(representationLowerCase() + 1));
        };
    }
    public ColumnIndex decrement() {
        return switch(this){
            case A, INVALID -> INVALID;
            default -> of((char)(representationLowerCase() - 1));
        };
    }
    public Character representationLowerCase() {
        char first = super.toString().charAt(0) ;
        return Character.toLowerCase(first);
    }

    private static Map<Character, ColumnIndex> representationToObject = null;
    static ColumnIndex of(Character representation) {
        if(representationToObject == null){
            initRepresentationToObject();
        }
        return representationToObject.getOrDefault(
            Character.toLowerCase(representation),
            INVALID
        );
    }
    private static void initRepresentationToObject() {
        representationToObject = Map.of(
            'a', A,
            'b', B,
            'c', C,
            'd', D,
            'e', E,
            'f', F,
            'g', G,
            'h', H
        );
    }
}
