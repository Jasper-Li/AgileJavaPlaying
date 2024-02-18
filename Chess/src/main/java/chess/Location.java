package chess;

import java.util.Optional;
import java.util.OptionalInt;

import static java.lang.System.out;

/**
 * Location on Board, represented by 2 characters: column rank,
 * @param column, 'a' - 'h'
 * @param rank, 1 - 8
 */
public record Location(ColumnIndex column, RankIndex rank) {
    public record DiagonalSteps(OptionalInt leftUp, OptionalInt leftDown, OptionalInt rightUp, OptionalInt rightDown){}
    public Location(String representation){
        this(ColumnIndex.of(Character.toLowerCase(representation.charAt(0))),
            RankIndex.of(representation.charAt(1))
        );
    }
    public boolean isInvalid() {
        return !isValid();
    }
    public boolean isValid() {
        return column.isValid() && rank.isValid();
    }

    public Optional<Location> move(int columnStep, int rankStep){
        if(!isValid() || (columnStep==0 && rankStep==0)) {
//            out.println("step is 0.");
            return Optional.empty();
        }
        final var columnAfter = column.move(columnStep);
        if(columnAfter.isEmpty()) {
//            out.println("OptionalColumn is empty.");
            return Optional.empty();
        }
        final var rankAfter = rank.move(rankStep);
        if(rankAfter.isEmpty()) {
//            out.println("OptionalRank is empty.");
            return Optional.empty();
        }

        return Optional.of( new Location(
            columnAfter.get(),
            rankAfter.get()
        ));
    }

    @Override
    public String toString() {
        final var columnValid = column.isValid();
        final var rankValid = rank.isValid();
        final String columnPart = columnValid ? column.toString() : "Invalid";
        final String rankPart = rankValid ? rank.toString() : "Invalid";
        return columnValid && rankValid ? STR."\{columnPart}\{rankPart}" : STR."[\{columnPart}, \{rankPart}]";
    }
    private OptionalInt getDiagonalStep(int a, int b){
        final var minAbs = Math.min(Math.abs(a), Math.abs(b));
        return minAbs > 0 ? OptionalInt.of(minAbs) : OptionalInt.empty();
    }
    public DiagonalSteps getDiagonalSteps() {
        final var stepsToColumnLeft = Index.MIN_INDEX - column.getInternalIndex();
        final var stepsToColumnRight = Index.MAX_INDEX - column.getInternalIndex();
        final var stepsToRankDown = Index.MIN_INDEX - rank.getInternalIndex();
        final var stepsToRankUp = Index.MAX_INDEX - rank.getInternalIndex();
        return  new DiagonalSteps(
            getDiagonalStep(stepsToColumnLeft, stepsToRankUp),
            getDiagonalStep(stepsToColumnLeft, stepsToRankDown),
            getDiagonalStep(stepsToColumnRight, stepsToRankUp),
            getDiagonalStep(stepsToColumnRight, stepsToRankDown)
        );
    }


}
