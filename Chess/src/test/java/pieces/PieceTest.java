package pieces;

import chess.Board;
import chess.ColumnIndex;
import chess.Location;
import chess.RankIndex;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

class PieceTest {
    @Test
    void testEquals() {
        record Check(char a, char b, boolean isEqual){}
        Check[] checks = {
            // equals
            new Check('.', '.', true),
            new Check('k', 'k', true),
            new Check('q', 'q', true),
            new Check('n', 'n', true),
            new Check('r', 'r', true),
            new Check('b', 'b', true),
            new Check('p', 'p', true),
            new Check('K', 'K', true),
            new Check('Q', 'Q', true),
            new Check('N', 'N', true),
            new Check('R', 'R', true),
            new Check('B', 'B', true),
            new Check('P', 'P', true),
            // not equal others
            new Check('p', '.', false),
            new Check('p', 'k', false),
            new Check('p', 'q', false),
            new Check('p', 'n', false),
            new Check('p', 'r', false),
            new Check('p', 'b', false),
            new Check('p', 'K', false),
            new Check('p', 'Q', false),
            new Check('p', 'N', false),
            new Check('p', 'R', false),
            new Check('p', 'B', false),
            // self
            new Check('p', 'P', false),
        };
        for(final var check : checks){
            final var a = Piece.of(check.a);
            final var b = Piece.of(check.b);
            final var msg = STR."check \{check.a} \{check.b}";
            if(check.isEqual){
                assertEquals(a, b, msg);
                assertEquals(b, a, msg);
            } else {
                assertNotEquals(a, b, msg);
                assertNotEquals(b, a, msg);

            }
        }
    }

    @Test
    void getPoint() {
        record Check(char piece, Double point){}
        final Check[] checks = {
            new Check(Piece.R_QUEEN, 9.0),
            new Check(Piece.R_ROOK, 5.0),
            new Check(Piece.R_BISHOP, 3.0),
            new Check(Piece.R_KNIGHT, 2.5),
            new Check(Piece.R_PAWN, 1.0),
        };
        for (var check : checks) {
            assertEquals(check.point, Piece.of(check.piece).getPoint());
        }
    }

    @Test
    void testEquals2(){
        final var a = new Location(ColumnIndex.A, RankIndex.R2);
        final var b = new Location("a2");
        assertEquals(a, b);
    }

    @Test
    void pieceArray() {
        record Check(String representation, Piece[]pieces){}
        final var checks = List.of(
            new Check("RNBQKBNR", new Piece[]{
                new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK),
                new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK)
            }),
            new Check("rnbqkbnr", new Piece[]{
                new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE),
                new King(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE)
            }),
            new Check("pppppppp", new Piece[]{
                new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
                new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
            }),
            new Check("PPPPPPPP", new Piece[]{
                new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
                new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
            }),
            new Check("........", new Piece[]{
                new BlankPiece(), new BlankPiece(), new BlankPiece(), new BlankPiece(),
                new BlankPiece(), new BlankPiece(), new BlankPiece(), new BlankPiece(),
            }),
            new Check("rnb", new Piece[]{
                new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new BlankPiece(),
                new BlankPiece(), new BlankPiece(), new BlankPiece(), new BlankPiece(),
            }),
            new Check("P", new Piece[]{
                new Pawn(BLACK), new BlankPiece(), new BlankPiece(), new BlankPiece(),
                new BlankPiece(), new BlankPiece(), new BlankPiece(), new BlankPiece(),
            }),
            new Check("R N B Q K B N R 8", new Piece[]{
                new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK),
                new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK)
            })
        );

        checks.forEach(check->{
            final var pieces = Piece.of(check.representation);
            assertArrayEquals(check.pieces, pieces);
            final var representationLength = check.representation.length();
            final var representation = Piece.getStringForArray(pieces);
            if( representationLength == Board.GRIDS_COUNT_PER_LINE) {
                assertEquals(check.representation, representation);
            } else if(representationLength < Board.GRIDS_COUNT_PER_LINE) {
                assertTrue(representation.startsWith(check.representation));
            }
        });
    }
}