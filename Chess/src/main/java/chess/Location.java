package chess;

import java.util.Optional;

import static java.lang.System.out;

/**
 * Location on Board, represented by 2 characters: column rank,
 * @param column, 'a' - 'h'
 * @param rank, 1 - 8
 */
public record Location(ColumnIndex column, RankIndex rank) {
    public Location(String representation){
        this(ColumnIndex.of(Character.toLowerCase(representation.charAt(0))),
            RankIndex.of(representation.charAt(1))
        );
    }
    public boolean isValid() {
        return column != ColumnIndex.INVALID && rank != RankIndex.INVALID;
    }


//    Location moveKing(Direction direction){
//        Location next = switch(direction){
//            case UP -> new Location(column, rank.increment());
//            case DOWN -> new Location(column, rank.decrement());
//            case LEFT -> new Location(column.decrement(), rank);
//            case RIGHT -> new Location(column.increment(), rank);
//            case UP_LEFT, LEFT_UP -> new Location(column.decrement(), rank.increment());
//            case UP_RIGHT, RIGHT_UP -> new Location(column.increment(), rank.increment());
//            case DOWN_LEFT, LEFT_DOWN -> new Location(column.decrement(), rank.decrement());
//            case DOWN_RIGHT, RIGHT_DOWN -> new Location(column.increment(), rank.decrement());
//            default -> this;
//        };
//        return next.isValid() ? next : this;
//    }
    public Optional<Location> move(int columnStep, int rankStep){
        if(!isValid() || (columnStep==0 && rankStep==0)) {
            return Optional.empty();
        }
        final var optionalColumn = Index.of(column.getInternalIndex() + columnStep);
        if(optionalColumn.isEmpty()) return Optional.empty();
        final var optionalRank = Index.of(rank.getInternalIndex() + rankStep);
        if(optionalRank.isEmpty()) return Optional.empty();
        final var columnAfter = optionalColumn.get();
        final var rankAfter = optionalRank.get();
//        out.println(STR."\{toString()} + {column: \{columnStep}, rank: \{rankStep}} => column: \{columnAfter}, rank: \{rankAfter}");
        return Optional.of(new Location(
            columnAfter.toColumnIndex(),
            rankAfter.toRankIndex()
            ));
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
