package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static chess.ColumnIndex.*;
import static chess.RankIndex.*;

public abstract class Index {
    public static final int MAX_INDEX = Board.GRIDS_COUNT_PER_LINE - 1;
    public static final int MIN_INDEX = 0;
    public static final Character INVALID_INDEX_REPRESENTATION = '.';
    public static final int INVALID_INDEX = -1;
    Integer index;
    protected Index(int index) {
        this.index = index;
    }

    int getInternalIndex() {
        return index;
    }

    static boolean isValid(int index){
        return 0<= index && index <= MAX_INDEX;
    }
    boolean isValid() {
        return isValid(index);
    }

    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Index that &&  this.index.equals(that.index);
    }

    protected int getMoveDestinationIndex(int step) {
        final var after = index + step;
        return isValid(after) ? after : INVALID_INDEX;
    }

    protected int getNextIndex() {
        return 0<=index && index < MAX_INDEX
            ? index + 1
            : INVALID_INDEX;
    }
    /**
     * @return List<Integer>  possible steps.
     */
    public List<Integer> getPossibleMoves() {
        final List<Integer> moves = new ArrayList<>(Board.GRIDS_COUNT_PER_LINE - 1);
        for(int i=MIN_INDEX; i <= MAX_INDEX; ++i){
            final var move = i - index;
            if(move != 0) {
                moves.addLast(move);
            }
        }
        return moves;
    }
    public boolean isColumnIndex() {
        return this.getClass().equals(ColumnIndex.class);
    }
    public boolean isRankIndex() {
        return this.getClass().equals(RankIndex.class);
    }

    @Override
    public int hashCode() {
        return index.hashCode();
    }
}
