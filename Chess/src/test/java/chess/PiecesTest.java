package chess;

import chess.Pieces;
import org.junit.jupiter.api.Test;
import pieces.Piece;

import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static pieces.Type.*;
import static pieces.Color.*;

public class PiecesTest {
    protected Pieces createPieces() {
        return new Pieces();
    }
    protected Pieces createPieces(String representation) {
        return new Pieces(representation);
    }
    @Test
    void defaultConstructor() {
        final var blankPieces = createPieces();
        out.println(STR."Checking class: \{blankPieces.getClass().getName()}");
        assertEquals("", blankPieces.toString());
        assertEquals("", blankPieces.toPrettyString());
    }

    @Test
    void setAndConstructorFromString() {
        String[] checks = {
            "RNBQKBNR",
            "rnbqkbnr",
            "pppppppp",
            "PPPPPPPP",
            "........",
            "rnb",
            "P",
        };
        for(final var check : checks) {
            final var pieces = createPieces();
            pieces.set(check);
            assertEquals(check, pieces.toString());
            final var pieces2 = createPieces(check);
            assertEquals(check, pieces2.toString());
        }
    }
    @Test
    void constructorFromPrettyString() {
        record Check(String prettyString, String normalString){}
        Check[] checks = {
            new Check(". K R . . . . . \r", ".KR....."),
            new Check(". K R . . . . . 4", ".KR....."),
            new Check("r n b q k b n r ", "rnbqkbnr"),
            new Check("r n b q k b n r", "rnbqkbnr"),
        };
        for(final var check: checks){
            assertEquals(new Pieces(check.prettyString), new Pieces(check.normalString));
        }
    }

    @Test
    void put() {
        record Check(Piece piece, int index){}
        final Check[] checks = {
            new Check(new Piece(BLACK, PAWN), 0),
            new Check(new Piece(WHITE, QUEEN), 3),
        };
        final var pieces = createPieces("........");
        for(final var check : checks) {
            assertNotEquals(check.piece, pieces.get(check.index));
            pieces.put(check.index, check.piece);
            assertEquals(check.piece, pieces.get(check.index));
        }
    }

    @Test
    void testEquals() {
        record Check(String left, String right, boolean expected){}
        final Check[] checks = {
            new Check("rnbqkbnr", "rnbqkbnr", true) ,
            new Check("rnbqkbnR", "rnbqkbnr", false) ,
            new Check("........", "........", true) ,
        };
        for(final var check : checks){
            final var left = createPieces(check.left);
            final var right = createPieces(check.right);
            if(check.expected){
                assertEquals(left, right);
                assertEquals(right, left);
            } else {
                assertNotEquals(left, right);
                assertNotEquals(right, left);
            }
        }
        assertEquals(createPieces(), createPieces());
    }

    @Test
    void countValidPieces() {
        record Check(String pieces, int count){};
        final Check[] checks={
            new Check("........", 0),
            new Check("p.......", 1),
            new Check("...p....", 1),
            new Check("rnbqkbnr", 8),
        };
        for(final var check : checks) {
            assertEquals(check.count, createPieces(check.pieces).countValidPieces());
        }
    }
    @Test
    void count() {
        record PieceCount(Character piece, int count){};
        record Check(String pieces, List<PieceCount> pieceCounts){};
        final Check[] checks={
            new Check("........", List.of(
                new PieceCount('p', 0) ,
                new PieceCount('r', 0) ,
                new PieceCount('n', 0) ,
                new PieceCount('b', 0) ,
                new PieceCount('q', 0) ,
                new PieceCount('k', 0),
                new PieceCount('P', 0) ,
                new PieceCount('R', 0) ,
                new PieceCount('N', 0) ,
                new PieceCount('B', 0) ,
                new PieceCount('Q', 0) ,
                new PieceCount('K', 0),
                new PieceCount('.', 8)
            )),
            new Check("rnbqkbnr", List.of(
                new PieceCount('p', 0) ,
                new PieceCount('r', 2) ,
                new PieceCount('n', 2) ,
                new PieceCount('b', 2) ,
                new PieceCount('q', 1) ,
                new PieceCount('k', 1),
                new PieceCount('P', 0) ,
                new PieceCount('R', 0) ,
                new PieceCount('N', 0) ,
                new PieceCount('B', 0) ,
                new PieceCount('Q', 0) ,
                new PieceCount('K', 0),
                new PieceCount('.', 0)
            )),
            new Check("RNBQKBNR", List.of(
                new PieceCount('p', 0) ,
                new PieceCount('r', 0) ,
                new PieceCount('n', 0) ,
                new PieceCount('b', 0) ,
                new PieceCount('q', 0) ,
                new PieceCount('k', 0),
                new PieceCount('P', 0) ,
                new PieceCount('R', 2) ,
                new PieceCount('N', 2) ,
                new PieceCount('B', 2) ,
                new PieceCount('Q', 1) ,
                new PieceCount('K', 1),
                new PieceCount('.', 0)
            )),
        };
        for(final var check : checks) {
            final var pieces= createPieces(check.pieces);
            for (final var pieceCount : check.pieceCounts) {
                final var piece = new Piece(pieceCount.piece);
                assertEquals(pieceCount.count, pieces.count(piece));
            }
        }
    }

    @Test
    void getPieces() {
        record Check(String pieces, String white, String black){}
        final Check[] checks = {
            new Check("rnbqkbnr", "rnbqkbnr", ""),
            new Check("RNBQKBNR", "", "RNBQKBNR"),
            new Check("rnbqKBNR", "rnbq", "KBNR"),
        };
        for(final var check: checks){
            final var pieces = createPieces(check.pieces);
            assertEquals(createPieces(check.white), pieces.getPieces(WHITE));
            assertEquals(createPieces(check.black), pieces.getPieces(BLACK));
        }
    }

    @Test
    void append() {
        record Check(String start, String some, String after){}
        final Check[] checks = {
            new Check("", "pp", "pp"),
            new Check("pp", "", "pp"),
            new Check("pp", "kn", "ppkn")
        };
        for (final var check : checks) {
            final var start = new Pieces(check.start);
            final var some = new Pieces(check.some);
            start.append(some);
            final var after = new Pieces(check.after);
            assertEquals(after, start);
        }
    }

    @Test
    void toPrettyString() {
        record Check(String pieces, String prettyString){}
        Check[] checks = {
            new Check(".KR.....", ". K R . . . . . "),
            new Check("rnbqkbnr", "r n b q k b n r "),
            new Check("pppppppp", "p p p p p p p p "),
        };
        for(final var check: checks){
            assertEquals(check.prettyString, new Pieces(check.pieces).toPrettyString());
        }
    }

}