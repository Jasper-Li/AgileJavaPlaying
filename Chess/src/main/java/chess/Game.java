package chess;

public class Game {
    private final Board board = new Board();

    public void initialize() {
        var newBoard = """
            R N B Q K B N R 8
            P P P P P P P P 7
            . . . . . . . . 6
            . . . . . . . . 5
            . . . . . . . . 4
            . . . . . . . . 3
            p p p p p p p p 2
            r n b q k b n r 1
            a b c d e f g h""";
    }
    public Board getBoard() {
        return board;
    }
}
