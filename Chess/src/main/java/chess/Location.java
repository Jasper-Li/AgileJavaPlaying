package chess;

/**
 * Location on Board, represented by 2 characters: column rank,
 * @param column, 'a' - 'h'
 * @param rank, 1 - 8
 */
public record Location(ColumnIndex column, RankIndex rank) {
//    public Location(int column, int rank) {
//        this.column = column; // starts from 0
//        this.rank = rank;     // starts from 1
//    }
    public Location(String representation){
        this(ColumnIndex.of(Character.toLowerCase(representation.charAt(0))),
            RankIndex.of(representation.charAt(1))
        );
    }
    public boolean isValid() {
        return column != ColumnIndex.INVALID && rank != RankIndex.INVALID;
    }


    Location moveKing(Direction direction){
        Location next = switch(direction){
            case UP -> new Location(column, rank.increment());
            case DOWN -> new Location(column, rank.decrement());
            case LEFT -> new Location(column.decrement(), rank);
            case RIGHT -> new Location(column.increment(), rank);
            case UP_LEFT, LEFT_UP -> new Location(column.decrement(), rank.increment());
            case UP_RIGHT, RIGHT_UP -> new Location(column.increment(), rank.increment());
            case DOWN_LEFT, LEFT_DOWN -> new Location(column.decrement(), rank.decrement());
            case DOWN_RIGHT, RIGHT_DOWN -> new Location(column.increment(), rank.decrement());
            default -> this;
        };
        return next.isValid() ? next : this;
    }

    @Override
    public String toString() {
        final var columnValid = column.isValid();
        final var rankValid = rank.isValid();
        final String columnPart = columnValid ? column.representationLowerCase().toString() : "Invalid";
        final String rankPart = rankValid ? rank.representation().toString() : "Invalid";
        return columnValid && rankValid ? STR."\{columnPart}\{rankPart}" : STR."[\{columnPart}, \{rankPart}]";
    }
}
