package chess;

import pieces.Color;
import pieces.Piece;
import pieces.Type;

import static chess.Board.GRIDS_COUNT_PER_LINE;
import static pieces.Type.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Column extends Pieces {

    Column(List<Piece> pieces) {
        this.pieces.clear();
        this.pieces.addAll(pieces);
    }
    Column(){
        super();
    }
    public Column(String representation) {
       super(representation);
    }

    public EnumMap<Type, Integer> getTypeCount(Color color) {
        EnumMap<Type, Integer> map = new EnumMap<>(Type.class);
        for(var piece : pieces) {
            if (piece.isEmpty() || piece.color() != color) continue;
            final var type = piece.type();
            var count = map.getOrDefault(type, 0);
            ++count;
            map.put(type, count);
        }
        return map;
    }

    public static double getPoint(EnumMap<Type, Integer> piecesCount, Type type){
        var point = type.getPoint();
        if(type == PAWN && piecesCount.getOrDefault(type, 0) > 1) {
            point = 0.5;
        }
        return point;
    }
    public double getStrength(Color color) {
        final var piecesCount = getTypeCount(color);
        var points = 0.0;
        for(var piece : this.pieces) {
            if (piece.isEmpty() || piece.color() != color)  continue;
            final var point = getPoint(piecesCount, piece.type());
            piece.setStrength(point);
            points += point;
        }
        return points;
    }
}
