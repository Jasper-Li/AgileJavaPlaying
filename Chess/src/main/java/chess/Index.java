package chess;

import java.util.Optional;
import static chess.ColumnIndex.*;
import static chess.RankIndex.*;

public class Index {
    public static final int MAX_INDEX = Board.GRIDS_COUNT_PER_LINE - 1;
    public static final int MIN_INDEX = 0;
    int index;
    record IndexToColumnRankIndex(ColumnIndex columnIndex, RankIndex rankIndex) {}
    static IndexToColumnRankIndex[] indexToColumnRankIndex = null;
    private Index(int index) {
        this.index = index;
    }

    static Optional<Index> of(int index) {
        return isValid(index) ? Optional.of(new Index(index)) :  Optional.empty();
    }
    int value() {
        return index;
    }

    static boolean isValid(int index){
        return 0<= index && index <= MAX_INDEX;
    }
    boolean isValid() {
        return isValid(index);
    }

    Optional<Index> move(int step) {
        if(step == 0) return Optional.empty();
        return of(index + step);
    }
    ColumnIndex toColumnIndex() {
        if(indexToColumnRankIndex == null){
            initIndexToColumnRankIndex();
        }
        return indexToColumnRankIndex[index].columnIndex;
    }
    RankIndex toRankIndex() {
        if(indexToColumnRankIndex == null){
            initIndexToColumnRankIndex();
        }
        return indexToColumnRankIndex[index].rankIndex;
    }
    static void initIndexToColumnRankIndex() {
        indexToColumnRankIndex = new IndexToColumnRankIndex[] {
            new IndexToColumnRankIndex(A, R1),
            new IndexToColumnRankIndex(B, R2),
            new IndexToColumnRankIndex(C, R3),
            new IndexToColumnRankIndex(D, R4),
            new IndexToColumnRankIndex(E, R5),
            new IndexToColumnRankIndex(F, R6),
            new IndexToColumnRankIndex(G, R7),
            new IndexToColumnRankIndex(H, R8),
        };
    }

    @Override
    public String toString() {
        return STR."\{index}";
    }
}
