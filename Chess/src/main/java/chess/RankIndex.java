package chess;

import java.util.Calendar;

public enum RankIndex {
    R1(0),
    R2(1),
    R3(2),
    R4(3),
    R5(4),
    R6(5),
    R7(6),
    R8(7),
    INVALID(-1);

    final private int internalIndex;
    RankIndex(int internalIndex) {
        this.internalIndex = internalIndex;
    }
    boolean isValid() {
        return this != INVALID;
    }
    public int getInternalIndex() {
        return internalIndex;
    }
    public RankIndex increment() {
        if(internalIndex == 7 || internalIndex == -1){
            return INVALID;
        }
        var representation =(char)(representation() + 1);
        return RankIndex.of(representation);
    }
    public RankIndex decrement() {
        if(internalIndex == 0 || internalIndex == -1){
            return INVALID;
        }
        var representation =(char)(representation() - 1);
        return RankIndex.of(representation);
    }

    Character representation() {
        return switch (this) {
            case R1 -> '1' ;
            case R2 -> '2' ;
            case R3 -> '3' ;
            case R4 -> '4' ;
            case R5 -> '5' ;
            case R6 -> '6' ;
            case R7 -> '7' ;
            case R8 -> '8' ;
           default -> 'X';
        };
    }
    static RankIndex of(Character representation) {
        return switch (representation) {
            case '1' -> R1;
            case '2' -> R2;
            case '3' -> R3;
            case '4' -> R4;
            case '5' -> R5;
            case '6' -> R6;
            case '7' -> R7;
            case '8' -> R8;
            default -> INVALID;
        };
    }
}
