package chess;

import java.util.Optional;

public class ColumnIndex extends  Index {
    public static final ColumnIndex A = new ColumnIndex(0);
    public static final ColumnIndex B = new ColumnIndex(1);
    public static final ColumnIndex C = new ColumnIndex(2);
    public static final ColumnIndex D = new ColumnIndex(3);
    public static final ColumnIndex E = new ColumnIndex(4);
    public static final ColumnIndex F = new ColumnIndex(5);
    public static final ColumnIndex G = new ColumnIndex(6);
    public static final ColumnIndex H = new ColumnIndex(7);
    public static final ColumnIndex INVALID = new ColumnIndex(INVALID_INDEX);

    public ColumnIndex(int index) {
        super(index);
    }

    static ColumnIndex of(char representation) {
        return new ColumnIndex(representation - 'a');
    }

    public Optional<ColumnIndex> move(int step) {
        final var destination = getMoveDestinationIndex(step);
        return isValid(destination) ? Optional.of(new ColumnIndex(destination)) : Optional.empty();
    }

    public ColumnIndex next() {
        return new ColumnIndex(getNextIndex());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        final char buf = isValid() ? (char) ('a' + index) : INVALID_INDEX_REPRESENTATION;
        return Character.toString(buf);
    }
}