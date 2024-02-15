package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.Piece;
import pieces.Type;
import util.StringUtil;

import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static util.StringUtil.NEW_LINE;
import static chess.ColumnIndex.*;
import static chess.RankIndex.*;
import static chess.Direction.*;

public class BoardTest {
    private static final String boardRepresentationTest_5_4 = util.System.isWindows() ?
            """ 
            .KR.....\r
            P.PB....\r
            .P..Q...\r
            ........\r
            .....nq.\r
            .....p..\r
            ......p.\r
            ....rk..\r
            """
            :
            """
            .KR.....
            P.PB....
            .P..Q...
            ........
            .....nq.
            .....p..
            ......p.
            ....rk..
            """;

    private Board board;
    @BeforeEach
    void setUp() {
        board = new Board();
    }
    @Test
    void create() {
        assertEquals(Board.RANK_COUNT, board.countRanks());
        assertEquals(0, board.countAllPieces());

        Piece.resetPiecesCount(0, 0);
        board.initialize();
        out.println(board);
        Rank rank2 = board.getRank(Board.WHITE_SECOND_RANK_INDEX_ON_BOARD);
        assertEquals("pppppppp", rank2.toString());
        assertEquals(
                "PPPPPPPP",
                board.getRank(Board.BLACK_SECOND_RANK_INDEX_ON_BOARD).toString());

        assertEquals(32, board.countAllPieces());
        assertEquals(16, Piece.getBlackPiecesCount());
        assertEquals(16, Piece.getWhitePiecesCount());

        board = new Board(boardRepresentationTest_5_4);
        assertEquals(boardRepresentationTest_5_4, Printer.print(board));
    }
    @Test
    void createByRepresentationWithDecoration() {
        final var representation = STR."""
                . K R . . . . . 8
                P . P B . . . . 7
                . P . . Q . . . 6
                . . . . . . . . 5
                . . . . . n q . 4
                . . . . . p . p 3
                . . . . . p p . 2
                . . . . r k . . 1
                a b c d e f g h
                """;
        final var printExpected = util.System.isWindows() ?
            """
            .KR.....\r
            P.PB....\r
            .P..Q...\r
            ........\r
            .....nq.\r
            .....p.p\r
            .....pp.\r
            ....rk..\r
            """
                :
            """
            .KR.....
            P.PB....
            .P..Q...
            ........
            .....nq.
            .....p.p
            .....pp.
            ....rk..
            """;
        board = new Board(representation);
        assertEquals(printExpected, Printer.print(board));
        assertTrue(StringUtil.isEqualIgnoreEOL(representation, board.toPrettyString()));
    }

    @Test
    void countPiece() {
        board = new Board(boardRepresentationTest_5_4);
        var blackPawnCountExpected = 3;
        assertEquals(blackPawnCountExpected, board.count(new Piece(Color.BLACK, Type.PAWN)));
    }
    @Test
    void retrievePiece() {
        board.initialize();
        var blackRook = new chess.Location("a8");
        var whiteQueen = new chess.Location("e1");
        assertEquals(new Piece(Color.BLACK, Type.ROOK), board.getPieceBy(blackRook));
        assertEquals(new Piece(Color.WHITE, Type.KING), board.getPieceBy(whiteQueen));
    }

    @Test
    void placePiece() {
        record PlacePieceCheck(Piece piece, Location location){};
        var blackKing = new Piece(Color.BLACK, Type.KING);
        var blackRook = new Piece(Color.BLACK, Type.ROOK);
        var whiteKing = new Piece(Color.WHITE, Type.KING);
        PlacePieceCheck[] checks = {
            new PlacePieceCheck(blackKing, new Location("b6")),
            new PlacePieceCheck(blackRook, new Location("b5")),
            new PlacePieceCheck(whiteKing, new Location("c4")),
        };
        int pieceCount = 0;
        assertEquals(pieceCount, board.countAllPieces());
        for (var check: checks) {
            board.placePiece(check.piece(), check.location());
            ++pieceCount;
            assertEquals(pieceCount, board.countAllPieces());
            assertEquals(check.piece(), board.getPieceBy(check.location()));
        }

        final var boardRepresentation =
            STR."""
            ........\{NEW_LINE}\
            ........\{NEW_LINE}\
            .K......\{NEW_LINE}\
            .R......\{NEW_LINE}\
            ..k.....\{NEW_LINE}\
            ........\{NEW_LINE}\
            ........\{NEW_LINE}\
            ........\{NEW_LINE}""";
        assertEquals(boardRepresentation, Printer.print(board));
    }
    @Test
    void countPoints() {
        var representation = """
                . K R . . . . . 8
                P . P B . . . . 7
                . P . . Q . . . 6
                . . . . . . . . 5
                . . . . . n q . 4
                . . . . . p . p 3
                . . . . . p p . 2
                . . . . r k . . 1
                a b c d e f g h
                """;
        board = new Board(representation);
        final Column column0 = board.getColumn(ColumnIndex.A);
        final Column columnExpected = new Column("......P.");
        assertEquals(columnExpected, column0);

        final var blackKing = board.getPieceBy(new Location("b8"));
        assertEquals("K", blackKing.toString());
        assertEquals(0.0, blackKing.strength());
        final var blackBishop = board.getPieceBy(new Location("d7"));
        assertEquals("B", blackBishop.toString());
        assertEquals(0.0,blackBishop.strength());

        final var blackPoints = 20.0;
        final var whitePoints = 19.5;
        assertEquals(whitePoints, board.getStrength(Color.WHITE));
        assertEquals(blackPoints, board.getStrength(Color.BLACK));

        assertEquals(0.0, blackKing.strength());
        assertEquals(3.0,blackBishop.strength());

        // for getPieces
        final var piecesWhite = board.getPieces(Color.WHITE);
        assertEquals(8, piecesWhite.size());
        final var piecesBlack = board.getPieces(Color.BLACK);
        assertEquals(7, piecesBlack.size());

        isSorted(piecesWhite);
        isSorted(piecesBlack);
    }
    private void isSorted(List<Piece> pieces) {
        final var size =  pieces.size();
        for(int i = 0; i<size-1; ++i) {
            final var piece1 = pieces.get(i);
            final var piece2 = pieces.get(i+1);
            final var strength1 = piece1.strength();
            final var strength2 = piece2.strength();
            assertTrue(
                strength1 >= strength2,
                    STR."Expect \{strength1} of \{piece1} >=  \{strength2} of \{piece2}"
            );
        }
    }
    @Test
    void moveKing() {
        record CheckNextPosition(String boardAfter, List<Direction> directions){};
        record CheckMoving(String boardStart, Location fromHere, CheckNextPosition[] nextPositions){};

        CheckMoving[] checkMovings = {
            new CheckMoving(
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . K . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1
                 a b c d e f g h""", new Location(E, R4),
                new CheckNextPosition[]{
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . . K . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(UP)),
                    new CheckNextPosition(
                        """
                        . . . . . . . . 8
                        . . . . . . . . 7
                        . . . . . . . . 6
                        . . . . . . . . 5
                        . . . . . . . . 4
                        . . . . K . . . 3
                        . . . . . . . . 2
                        . . . . . . . . 1
                        a b c d e f g h""",
                        List.of(DOWN)),
                    new CheckNextPosition(
                        """
                        . . . . . . . . 8
                        . . . . . . . . 7
                        . . . . . . . . 6
                        . . . . . . . . 5
                        . . . K . . . . 4
                        . . . . . . . . 3
                        . . . . . . . . 2
                        . . . . . . . . 1
                        a b c d e f g h""",
                        List.of(LEFT)),
                    new CheckNextPosition(
                        """
                        . . . . . . . . 8
                        . . . . . . . . 7
                        . . . . . . . . 6
                        . . . . . . . . 5
                        . . . . . K . . 4
                        . . . . . . . . 3
                        . . . . . . . . 2
                        . . . . . . . . 1
                        a b c d e f g h""",
                        List.of(RIGHT)),
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . K . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(UP_LEFT, LEFT_UP)),
                    new CheckNextPosition(
                        """
                        . . . . . . . . 8
                        . . . . . . . . 7
                        . . . . . . . . 6
                        . . . . . K . . 5
                        . . . . . . . . 4
                        . . . . . . . . 3
                        . . . . . . . . 2
                        . . . . . . . . 1
                        a b c d e f g h""",
                        List.of(UP_RIGHT, RIGHT_UP)),
                    new CheckNextPosition(
                        """
                        . . . . . . . . 8
                        . . . . . . . . 7
                        . . . . . . . . 6
                        . . . . . . . . 5
                        . . . . . . . . 4
                        . . . K . . . . 3
                        . . . . . . . . 2
                        . . . . . . . . 1
                        a b c d e f g h""",
                        List.of(DOWN_LEFT, LEFT_DOWN)),
                    new CheckNextPosition(
                        """
                        . . . . . . . . 8
                        . . . . . . . . 7
                        . . . . . . . . 6
                        . . . . . . . . 5
                        . . . . . . . . 4
                        . . . . . K . . 3
                        . . . . . . . . 2
                        . . . . . . . . 1
                        a b c d e f g h""",
                        List.of(DOWN_RIGHT, RIGHT_DOWN)),
                }// end nextPositions
            ), // end CheckMoving
            new CheckMoving(
                """
                 K . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1
                 a b c d e f g h""", new Location(A, R8),
                new CheckNextPosition[]{
                    new CheckNextPosition(
                        """
                         K . . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(UP_LEFT, LEFT_UP, UP, UP_RIGHT, RIGHT_UP,LEFT, LEFT_DOWN, DOWN_LEFT)),
                    new CheckNextPosition(
                        """
                         . K . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(RIGHT)),
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         . K . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(RIGHT_DOWN, DOWN_RIGHT)),
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         K . . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(DOWN)),
                }// end nextPositions
            ), // end CheckMoving
            new CheckMoving(
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . K 1
                 a b c d e f g h""", new Location(H, R1),
                new CheckNextPosition[]{
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . . K 1
                         a b c d e f g h""",
                        List.of(UP_RIGHT, RIGHT_UP, RIGHT, RIGHT_DOWN, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT_DOWN)),
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . K 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(UP)),
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . K . 2
                         . . . . . . . . 1
                         a b c d e f g h""",
                        List.of(UP_LEFT, LEFT_UP)),
                    new CheckNextPosition(
                        """
                         . . . . . . . . 8
                         . . . . . . . . 7
                         . . . . . . . . 6
                         . . . . . . . . 5
                         . . . . . . . . 4
                         . . . . . . . . 3
                         . . . . . . . . 2
                         . . . . . . K . 1
                         a b c d e f g h""",
                        List.of(LEFT)),
                }// end nextPositions
            ), // end CheckMoving
        };
        for (final var checkMoving : checkMovings) {
            for(final var nextPosition : checkMoving.nextPositions) {
                for (final var direction : nextPosition.directions) {
                    var board = new Board(checkMoving.boardStart);
                    board.moveKing(checkMoving.fromHere, direction);
                    assertEquals(new Board(nextPosition.boardAfter), board, STR."Failed on testing direction \{direction} from \{checkMoving.fromHere} of board:\n\{checkMoving.boardStart}" );
                }
            }
        }

}

    @Test
    void testEquals() {
        record Check(String boardA, String boardB, boolean expected){}
        Check[] checks = {
            new Check(
                """
                    . K R . . . . . 8
                    P . P B . . . . 7
                    . P . . Q . . . 6
                    . . . . . . . . 5
                    . . . . . n q . 4
                    . . . . . p . p 3
                    . . . . . p p . 2
                    . . . . r k . . 1
                    a b c d e f g h
                    """, """
                    . K R . . . . . 8
                    P . P B . . . . 7
                    . P . . Q . . . 6
                    . . . . . . . . 5
                    . . . . . n q . 4
                    . . . . . p . p 3
                    . . . . . p p . 2
                    . . . . r k . . 1
                    a b c d e f g h
                    """, true
            ),
            new Check(
                """
                    . K R . . . . . 8
                    P . P B . . . . 7
                    . P . . Q . . . 6
                    . . . . . . . . 5
                    . . . . . n q . 4
                    . . . . . p . p 3
                    . . . . . p p . 2
                    . . . . r k . . 1
                    a b c d e f g h
                    """, """
                    . K R . . . . . 8
                    P . P B . . . . 7
                    . P . . Q . . . 6
                    . . . . . . . . 5
                    . . . . . n q . 4
                    . . . . . p . p 3
                    . . . . . p p . 2
                    . . . . r k . P 1
                    a b c d e f g h
                    """, false
            ),
        };
        for(final var check: checks) {
            var boardA = new Board(check.boardA);
            var boardB = new Board(check.boardB);
            assertEquals(check.expected, boardA.equals(boardB));
            assertEquals(check.expected, boardB.equals(boardA));
            if(check.expected){
                assertEquals(boardA, boardB);
            } else {
                assertNotEquals(boardA, boardB);
            }
        }

    }
}