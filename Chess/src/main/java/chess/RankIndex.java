package chess;

import java.util.Optional;

public class RankIndex extends Index{
    public static final RankIndex R1 = new RankIndex(0);
    public static final RankIndex R2 = new RankIndex(1);
    public static final RankIndex R3 = new RankIndex(2);
    public static final RankIndex R4 = new RankIndex(3);
    public static final RankIndex R5 = new RankIndex(4);
    public static final RankIndex R6 = new RankIndex(5);
    public static final RankIndex R7 = new RankIndex(6);
    public static final RankIndex R8 = new RankIndex(7);
    public static final RankIndex INVALID = new RankIndex(INVALID_INDEX);

    protected RankIndex(int index) {
        super(index);
    }

    static RankIndex of(char representation) {
        return new RankIndex(representation - '1');
    }

    @Override
    public String toString() {
        return isValid()
            ? Integer.valueOf(index + 1).toString()
            : INVALID_INDEX_REPRESENTATION.toString();
    }

    public Optional<RankIndex> move(int step){
        final var destination = getMoveDestinationIndex(step);
        return isValid(destination) ? Optional.of(new RankIndex(destination)) : Optional.empty();
    }
    public RankIndex next() {
        return new RankIndex(getNextIndex());
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
