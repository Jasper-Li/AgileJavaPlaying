package pieces;

import chess.Location;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece{
    public King(Color color) {
        super(color, Piece.R_KING);
    }
    @Override
    public Set<Location> getPossibleMoves(Location current) {
        final Set<Location> locations = new HashSet<>();
        final int[] validSteps={-1, 0, 1};
        for (int validStepColumn : validSteps) {
            for (int validStepRank : validSteps) {
                final var optionalAfter = current.move(validStepColumn, validStepRank);
                if (optionalAfter.isEmpty()) continue;
                locations.add(optionalAfter.get());
            }
        }
        return locations;
    }
}
