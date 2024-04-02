package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.King;
import pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static util.StringUtil.NEW_LINE;

public class BoardTest {
    public static final String board_5_4 = """
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
    private static final String boardRepresentationTest_5_4 = STR."""
        .KR.....\{NEW_LINE}\
        P.PB....\{NEW_LINE}\
        .P..Q...\{NEW_LINE}\
        ........\{NEW_LINE}\
        .....nq.\{NEW_LINE}\
        .....p..\{NEW_LINE}\
        ......p.\{NEW_LINE}\
        ....rk..\{NEW_LINE}\
        """;

    private Board board;
    @BeforeEach
    void setUp() {
        board = new Board();
    }
    @Test
    void defaultConstructor() {
        final var board = new Board();
        assertEquals(0, board.countAllPieces());


        board.initialize();

        Rank rank2 = board.getRank(Board.WHITE_SECOND_RANK_INDEX_ON_BOARD);
        assertEquals("pppppppp", rank2.toString());
        assertEquals(
            "PPPPPPPP",
            board.getRank(Board.BLACK_SECOND_RANK_INDEX_ON_BOARD).toString());

        assertEquals(32, board.countAllPieces());
        assertEquals(new Board(GameTest.initialBoard), board);
    }
    @Test
    void constructorString(){
        final var board = new Board(boardRepresentationTest_5_4);
        assertEquals(boardRepresentationTest_5_4, Printer.print(board));
    }
    @Test
    void constructorByRepresentationWithDecoration() {
        final var representation = """
            . K R . . . . . 8
            P . P B . . . . 7
            . P . . Q . . . 6
            . . . . . . . . 5
            . . . . . n q . 4
            . . . . . p . . 3
            . . . . . . p . 2
            . . . . r k . . 1
            a b c d e f g h
            """;
        final var board = new Board(representation);
        assertEquals(boardRepresentationTest_5_4, Printer.print(board));
        assertTrue(StringUtil.isEqualIgnoreEOL(representation, board.toPrettyString()));
    }
    @Test
    void initializeBlankBoard() {
        final var board = new Board();
        board.initializeBlankBoard();
        final var representation = """
            ........ 8
            ........ 7
            ........ 6
            ........ 5
            ........ 4
            ........ 3
            ........ 2
            ........ 1
            """;
        assertEquals(new Board(representation), board);
    }

    @Test
    void countPiece() {
        final var board = new Board(boardRepresentationTest_5_4);
        Map<Character, Integer> pieceCount = Map.of(
            'K', 1, 'R', 1,
            'P', 3, 'B', 1, 'Q', 1,
            'n', 1, 'q', 1,
            'p', 2, 'r', 1, 'k', 1

        );
        pieceCount.forEach((pieceCharacter, count)->{
            final var piece = Piece.of (pieceCharacter);
            assertEquals(count, board.count(piece));
        });

    }
    @Test
    void get() {
        final var board = new Board();
        board.initialize();
        Map<String, Character> locationToPiece = Map.of(
            "a1", 'r', "a2", 'p', "a7", 'P', "a8", 'R',
            "e1", 'k', "e2", 'p', "e7", 'P', "e8", 'K',
            "a3", '.'
        );
        locationToPiece.forEach((location, piece)->{
            assertEquals(Piece.of(piece), board.get(new Location(location)));
        });
    }

    @Test
    void getPieces() {
        final var board = new Board(board_5_4);
        final var piecesWhite = board.getPieces(Color.WHITE);
        assertEquals(8, piecesWhite.size());
        final var piecesBlack = board.getPieces(Color.BLACK);
        assertEquals(7, piecesBlack.size());
    }
    private void isSorted(Pieces pieces) {
        final var size = pieces.size();
        for (int i = 0; i < size - 1; ++i) {
            final var piece1 = pieces.get(i);
            final var piece2 = pieces.get(i + 1);
            final var strength1 = piece1.getStrength();
            final var strength2 = piece2.getStrength();
            assertTrue(
                strength1 >= strength2,
                STR."Expect \{strength1} of \{piece1} >=  \{strength2} of \{piece2} on pieces: \{pieces}"
            );
        }
    }

    @Test
    void put() {
        record PlacePieceCheck(Character piece, String location){}
        PlacePieceCheck[] checks = {
            new PlacePieceCheck('K', "b6"),
            new PlacePieceCheck('R', "b5"),
            new PlacePieceCheck('k', "c4"),
        };
        int pieceCount = 0;
        final var board = new Board();
        board.initializeBlankBoard();
        assertEquals(pieceCount, board.countAllPieces());
        for (var check: checks) {
            final var location = new Location(check.location());
            board.put(Piece.of(check.piece()), location);
            ++pieceCount;
            assertEquals(pieceCount, board.countAllPieces());
            assertEquals(check.piece(), board.get(location).toChar());
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

    } // end of testEquals

//    @Disabled("dev")
    @Test
    void getLocations() {
        record Check(String board, Set<String> locations) {}
        final Check[] checks = {
            new Check(
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . K K K . . 5
                 . . . K . K . . 4
                 . . . K K K . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1
                 a b c d e f g h""",
                Set.of("e3", "e5", "d3", "d4", "d5", "f3", "f4", "f5")
            ),
        };
        for(final var check : checks) {
            final Set<Location> locationsExpected = check.locations.stream()
                .map(Location::new)
                .collect(Collectors.toSet());
            final var result = new Board(check.board)
                .getLocations(Piece.of('K'));
            assertEquals(locationsExpected, result);
        }
    }

    @Test
    void iterator() {
        final var validBoardIterator = board.iterator();
        assertFalse(validBoardIterator.hasNext());

        board.set(board_5_4);
        final var validBoardIterator2 = board.iterator();
        assertTrue(validBoardIterator2.hasNext());
        List<String> piecesExpected = new ArrayList<>();
        while(validBoardIterator2.hasNext()) {
            final var current = validBoardIterator2.next();
            piecesExpected.add(current.toString());
        }
        assertEquals(List.of(
            "r", "k", "p", "p", "p",
            "p", "n", "q", "P", "Q",
            "P", "P", "B", "K", "R"), piecesExpected);
    }
}