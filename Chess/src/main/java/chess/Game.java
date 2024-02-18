package chess;

import pieces.Color;

public class Game {
    private Board board = null;

    public void initialize() {
        board = new Board();
        board.initialize();
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    void calculateStrength() {
        calculateStrength(Color.BLACK);
        calculateStrength(Color.WHITE);
    }
    void calculateStrength(Color color){
        for(var columnIndex = ColumnIndex.A; columnIndex.isValid(); columnIndex = columnIndex.next())  {
            board.getColumn(columnIndex).calculateStrength(color);
        }
    }
    public double getStrength(Color color) {
        calculateStrength(color);
        double points = 0;
        for(var columnIndex = ColumnIndex.A; columnIndex.isValid(); columnIndex = columnIndex.next()) {
            points += board.getColumn(columnIndex).getStrength(color);
        }
        return points;
    }
}
