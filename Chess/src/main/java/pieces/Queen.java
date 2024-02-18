package pieces;

import chess.Location;

import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

public class Queen extends Piece{
    public Queen(Color color) {
        super(color, Piece.R_QUEEN);
    }

    @Override
    public Set<Location> getPossibleMoves(Location current) {
        final Set<Location> locations = new HashSet<>();
        final var possibleColumMoves = current.column().getPossibleMoves();
        final var possibleRankMoves = current.rank().getPossibleMoves();
//                out.println(STR."""
//                    possibleColumnMoves: \{possibleColumMoves}
//                    possibleRankMoves: \{possibleColumMoves}
//                    """);
        for(final var columnMove : possibleColumMoves){
            final var optionalAfter = current.move(columnMove, 0);
            if(optionalAfter.isPresent()) locations.add(optionalAfter.get());

        }
        for(final var rankMove : possibleRankMoves){
            final var optionalAfter = current.move(0, rankMove);
            if(optionalAfter.isPresent()) locations.add(optionalAfter.get());
        }

        final var diagonalStepsBuf = current.getDiagonalSteps();
        record Direction(OptionalInt steps, boolean isColumnForward, boolean isRankForward){}
        final Direction[] directions = {
            new Direction(diagonalStepsBuf.leftUp(), false, true),
            new Direction(diagonalStepsBuf.leftDown(), false, false),
            new Direction(diagonalStepsBuf.rightUp(), true, true),
            new Direction(diagonalStepsBuf.rightDown(), true, false),
        };
        for(final var direction : directions){
            if(direction.steps.isEmpty()) continue;
            final var columnStep = direction.isColumnForward ? 1 : -1;
            final var rankStep = direction.isRankForward ? 1 : -1;
            for(int i = 1; i <= direction.steps.getAsInt(); ++i) {
                final var optionalAfter = current.move(i * columnStep, i * rankStep) ;
                if(optionalAfter.isPresent()) locations.add(optionalAfter.get());
            }
        }
        return locations;
    }
}
