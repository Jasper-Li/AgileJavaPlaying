package chess;

import pieces.Color;
import pieces.Piece;
import util.StringUtil;

import java.util.*;

public class Board {
    public static final int GRIDS_COUNT_PER_LINE = 8;
    public static final RankIndex  WHITE_BACK_RANK_INDEX_ON_BOARD = RankIndex.R1;
    public static final RankIndex WHITE_SECOND_RANK_INDEX_ON_BOARD = RankIndex.R2;
    public static final RankIndex BLACK_SECOND_RANK_INDEX_ON_BOARD = RankIndex.R7;
    public static final RankIndex BLACK_BACK_RANK_INDEX_ON_BOARD = RankIndex.R8;
    private final List<Rank> ranks = new ArrayList<>(GRIDS_COUNT_PER_LINE);
    public Board() {
        for (int i = 0; i < GRIDS_COUNT_PER_LINE; ++i) {
            ranks.add(new Rank());
        }
    }

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
            if(ranks.size() == GRIDS_COUNT_PER_LINE) {
                ranks.get(linesIndex).set(lines[linesIndex]);
            } else {
                ranks.add(new Rank(lines[linesIndex]));
            }
        }
    }
    public Board initialize() {
        var rankIndex = RankIndex.R1;
        for (int i = 0; i < GRIDS_COUNT_PER_LINE; ++i) {
            final var rank = ranks.get(i);
            if (rankIndex.equals(RankIndex.R1)) {
                rank.set("rnbqkbnr");
            } else if (rankIndex.equals(RankIndex.R2)) {
                rank.set("pppppppp");
            } else if (rankIndex.equals(RankIndex.R7)) {
                rank.set("PPPPPPPP");
            } else if (rankIndex.equals(RankIndex.R8)) {
                rank.set("RNBQKBNR");
            } else {
                rank.set(Rank.BLANK_REPRESENTATION);
            }
            rankIndex = rankIndex.next();
        }
        return this;
    }
    public void initializeBlankBoard() {
        for(final var rank : ranks){
            rank.set(Rank.BLANK_REPRESENTATION);
        }
    }
    public int count(Piece piece) {
        int count = 0;
        for (var rank : ranks) {
            count += rank.count(piece);
        }
        return count;
    }

    public int countAllPieces() {
        int count = 0;
        for (var rank : ranks) {
            count += rank.countValidPieces();
        }
        return count;
    }
    public Piece get(Location location) {
        final var column = location.column();
        final var rank = location.rank();
        return getRank(rank).get(column);
    }
    public Column getColumn(ColumnIndex columnIndex) {
        List<Piece> pieces = new ArrayList<Piece>();
        for (var rank : ranks) {
            pieces.add(rank.get(columnIndex));
        }
        return new Column(pieces);
    }
    public Rank getRank(RankIndex rank) {
        return ranks.get(rank.getInternalIndex());
    }

    public Pieces getPieces(Color color){
        var pieces = new Pieces();
        for (final var rank : ranks)  {
            final var some = rank.getPieces(color);
            pieces.addAll(some);
        }
        pieces.sort();
        return pieces;
    }

    public void put(Piece piece, Location location) {
        final var column = location.column();
        final var rank = location.rank();
        getRank(rank).put(column, piece);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for(var i = ranks.size() - 1; i >=0; --i) {
            buffer.append(ranks.get(i).toString());
            buffer.append(StringUtil.NEW_LINE);
        }
        return buffer.toString();
    }

    public String toPrettyString() {
        StringBuilder buffer = new StringBuilder();
        for(var i = ranks.size() - 1; i >=0; --i) {
            buffer.append(ranks.get(i).toPrettyString());
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
            return this.ranks.equals(t.ranks);
        }
        return false;
    }

}