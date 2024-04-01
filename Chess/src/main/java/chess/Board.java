package chess;

import pieces.Color;
import pieces.Piece;
import util.StringUtil;

import java.util.*;

import static java.lang.System.out;

public class Board {
    public static final int GRIDS_COUNT_PER_LINE = 8;
    public static final RankIndex  WHITE_BACK_RANK_INDEX_ON_BOARD = RankIndex.R1;
    public static final RankIndex WHITE_SECOND_RANK_INDEX_ON_BOARD = RankIndex.R2;
    public static final RankIndex BLACK_SECOND_RANK_INDEX_ON_BOARD = RankIndex.R7;
    public static final RankIndex BLACK_BACK_RANK_INDEX_ON_BOARD = RankIndex.R8;
    private final Piece[][] pieces = new Piece[GRIDS_COUNT_PER_LINE][GRIDS_COUNT_PER_LINE];
    public Board() {}

    public Board(String boardRepresentation){
        set(boardRepresentation);
    }
    public void set(String boardRepresentation) {
        final String[] lines = boardRepresentation.split("\\r?\n");
        final var linesCount = lines.length;
        if(!(linesCount == GRIDS_COUNT_PER_LINE || linesCount == GRIDS_COUNT_PER_LINE + 1)) {
            throw new RuntimeException(STR."wrong lines count: \{linesCount} of boardRepresentation:\n\{boardRepresentation}");
        }
        for(int linesIndex = GRIDS_COUNT_PER_LINE - 1; linesIndex >=0; --linesIndex) {
            final var newRank = new Pieces(lines[linesIndex]);
            final var rank = pieces[GRIDS_COUNT_PER_LINE - 1 - linesIndex];
            for(int i=0; i<GRIDS_COUNT_PER_LINE; ++i) {
                rank[i] = newRank.get(i);
            }
        }
    }
    public Board initialize() {
        final var initialBoard =
            """
            RNBQKBNR
            PPPPPPPP
            ........
            ........
            ........
            ........
            pppppppp
            rnbqkbnr
            """;
        set(initialBoard);
        return this;
    }
    public void initializeBlankBoard() {
        final var blankBoard =
            """
            ........
            ........
            ........
            ........
            ........
            ........
            ........
            ........
            """;
        set(blankBoard);
    }
    public int count(Piece piece) {
        int count = 0;
        for(int i=0; i<GRIDS_COUNT_PER_LINE; ++i)
            for(int j=0; j<GRIDS_COUNT_PER_LINE; ++j){
                if(pieces[i][j].equals(piece)) ++count;
            }
        return count;
    }

    public int countAllPieces() {
        int count = 0;
        for(int i=0; i<GRIDS_COUNT_PER_LINE; ++i)
            for(int j=0; j<GRIDS_COUNT_PER_LINE; ++j){
                final var current = pieces[i][j];
                if(!(current == null || current.isEmpty())) ++count;
            }
        return count;
    }
    public Piece get(Location location) {
        final var column = location.column().getInternalIndex();
        final var rank = location.rank().getInternalIndex();
        return pieces[rank][column];
    }
    public Column getColumn(ColumnIndex columnIndex) {
        List<Piece> pieces = new ArrayList<Piece>();
        final var columnInternalIndex = columnIndex.getInternalIndex();
        for(int i = 0; i< GRIDS_COUNT_PER_LINE; ++i) {
            pieces.add(this.pieces[i][columnInternalIndex]);
        }
        return new Column(pieces);
    }
    public Rank getRank(RankIndex rankIndex) {
        List<Piece> pieces = Arrays.asList(this.pieces[rankIndex.getInternalIndex()]);
        return new Rank(pieces);
    }

    public Pieces getPieces(Color color){
        var pieces = new Pieces();
        for(int i=0; i<GRIDS_COUNT_PER_LINE; ++i)
            for(int j=0; j<GRIDS_COUNT_PER_LINE; ++j){
                final var currentPiece = this.pieces[i][j];
                if(currentPiece.getColor() == color) {
                    pieces.add(currentPiece);
                }
            }
        pieces.sort();
        return pieces;
    }

    public void put(Piece piece, Location location) {
        final var column = location.column().getInternalIndex();
        final var rank = location.rank().getInternalIndex();
        pieces[rank][column] = piece;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        for(int i=GRIDS_COUNT_PER_LINE - 1; i>=0; --i) {
            for(int j=0; j<GRIDS_COUNT_PER_LINE; ++j) {
                buffer.append(pieces[i][j].toString());
            }
            buffer.append(StringUtil.NEW_LINE);
        }
        return buffer.toString();
    }

    public String toPrettyString() {
        StringBuilder buffer = new StringBuilder();
        for(int i=GRIDS_COUNT_PER_LINE - 1; i>=0; --i) {
            for(int j=0; j<GRIDS_COUNT_PER_LINE; ++j) {
                buffer.append(pieces[i][j].toString());
                buffer.append(' ');
            }
            buffer.append(i+1);
            buffer.append(StringUtil.NEW_LINE);
        }
        buffer.append("a b c d e f g h");
        buffer.append(StringUtil.NEW_LINE);
        return buffer.toString();
    }

    public Set<Location> getLocations(Piece piece) {
        Set<Location> locations = new HashSet<>();
        return locations;
    }

    @Override
    public boolean equals(Object that){
        if(that instanceof Board t) {
            for(int i=GRIDS_COUNT_PER_LINE - 1; i>=0; --i) {
                for (int j = 0; j < GRIDS_COUNT_PER_LINE; ++j) {
                    if (!pieces[i][j].equals(t.pieces[i][j])) {
                        out.println(STR."Not equal at \{i} \{j}, this: \{pieces[i][j]} that: \{t.pieces[i][j]} ");
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

}