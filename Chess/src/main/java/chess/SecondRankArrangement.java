package chess;

import pieces.Type;

import java.util.Collections;
import java.util.List;

public class SecondRankArrangement implements  Arrangement{
    @Override
    public List<Type> names() {
        return Collections.nCopies(Board.GRIDS_COUNT_PER_LINE, Type.PAWN);
    }
}
