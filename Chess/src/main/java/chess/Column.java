package chess;

import pieces.Color;
import pieces.Pawn;
import pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Column extends Pieces {

    Column(){
        super();
    }
    Column(List<Piece> pieces) {
        super(pieces);
    }
    public Column(String representation) {
       super(representation);
    }

    public Map<String, Integer> getTypeCount(Color color) {
        Map<String, Integer> map = new HashMap<>();
        for(var piece : pieces) {
            if (piece.isEmpty() || piece.getColor() != color) continue;
            final var type = piece.getClass().getName();
            var count = map.getOrDefault(type, 0);
            ++count;
            map.put(type, count);
        }
        return map;
    }

    public void calculateStrength(Color color){
        for(var piece : this.pieces) {
            if (piece.getColor() != color) continue;
            double point = 0.0;
            if(piece.isPawn()){
                point = count(new Pawn(color)) > 1 ? 0.5 : 1.0;
            } else  {
                point = piece.getPoint();
            }
            piece.setStrength(point);
        }

    }
    public void calculateStrength(){
        calculateStrength(Color.BLACK);
        calculateStrength(Color.WHITE);
    }
    public double getStrength(Color color) {
        var points = 0.0;
        for(var piece : this.pieces) {
            if (piece.getColor() == color) {
                points += piece.getStrength();
            }
        }
        return points;
    }
}
