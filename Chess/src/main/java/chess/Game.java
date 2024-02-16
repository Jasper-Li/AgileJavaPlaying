package chess;

public class Game {
    private final Board board = new Board();

    public void initialize() {
        board.initialize();
    }
    public Board getBoard() {
        return board;
    }
}
