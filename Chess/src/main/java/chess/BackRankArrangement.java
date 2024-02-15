package chess;

import pieces.Type;

import java.util.ArrayList;
import java.util.List;

public class BackRankArrangement implements  Arrangement{
    @Override
    public List<Type> names() {
        return new ArrayList<>(List.of(
            Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN,
            Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK
        ));
    }
}
