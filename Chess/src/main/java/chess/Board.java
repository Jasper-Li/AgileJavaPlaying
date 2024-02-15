package chess;

import pieces.Color;
import pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board {
    public static final int GRIDS_COUNT_PER_LINE = 8;
    public static final RankIndex  WHITE_BACK_RANK_INDEX_ON_BOARD = RankIndex.R1;
    public static final RankIndex WHITE_SECOND_RANK_INDEX_ON_BOARD = RankIndex.R2;
    public static final RankIndex BLACK_SECOND_RANK_INDEX_ON_BOARD = RankIndex.R7;
    public static final RankIndex BLACK_BACK_RANK_INDEX_ON_BOARD = RankIndex.R8;
    private final List<Rank> ranks = new ArrayList<Rank>(GRIDS_COUNT_PER_LINE);
    public Board() {
        for (int i = 0; i < GRIDS_COUNT_PER_LINE; ++i) {
            ranks.add(new Rank());
        }
    }

    public Board(String boardRepresentation){
    }
    public void set(String boardRepresentation) {
        final String[] lines = boardRepresentation.split("\\r?\n");
        final var linesCount = lines.length;
        if(!(linesCount == GRIDS_COUNT_PER_LINE || linesCount == GRIDS_COUNT_PER_LINE + 1)) {
            throw new RuntimeException(STR."wrong lines count: \{linesCount} of boardRepresentation:\n\{boardRepresentation}");
        }
        for(int i = GRIDS_COUNT_PER_LINE - 1; i >=0; --i) {
            var rank = ranks.get(i);
            rank.set(lines[i]);
        }
    }
    public Board initialize() {
        var rankIndex = RankIndex.R1;
        for(int i = 0; i < GRIDS_COUNT_PER_LINE; ++i) {
            switch (rankIndex) {
                case RankIndex.R1-> {
                   ranks.set(i, new Rank(Color.WHITE, new BackRankArrangement()));
                }
                case RankIndex.R2-> {
                    ranks.set(i, new Rank(Color.WHITE, new SecondRankArrangement()));
                }
                case RankIndex.R7-> {
                    ranks.set(i, new Rank(Color.BLACK, new SecondRankArrangement()));
                }
                case RankIndex.R8-> {
                    ranks.set(i, new Rank(Color.BLACK, new BackRankArrangement()));
                }
                default -> ranks.set(i, new Rank());
            };
            rankIndex = rankIndex.increment();
        }
        return this;
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
    public Column getColumn(ColumnIndex columnIndex) {
        List<Piece> pieces = new ArrayList<Piece>();
        for (var rank : ranks) {
            pieces.add(rank.getPiece(columnIndex));
        }
        return new Column(pieces);
    }
    public Rank getRank(RankIndex rank) {
        return ranks.get(rank.getInternalIndex());
    }

    public Piece get(Location location) {
        final var column = location.column();
        final var rank = location.rank();
        return getRank(rank).getPiece(column);
    }
    public List<Piece> getPieces(Color color){
        List<Piece> pieces = new LinkedList<>();
        for (final var rank : ranks)  {
            final var some = rank.getPieces(color);
            pieces.addAll(some);
        }
        pieces.sort(Collections.reverseOrder());
        return pieces;
    }


    public void put(Piece piece, Location location) {
        final var column = location.column();
        final var rank = location.rank();
        getRank(rank).put(piece, column);
    }


    public double getStrength(Color color) {
        double points = 0;
        var columnIndex = ColumnIndex.A;
        for(int i = 0; i< GRIDS_COUNT_PER_LINE; ++i)  {
            final var column = getColumn(columnIndex);
            final var point = column.getStrength(color);
            points += point;
            columnIndex = columnIndex.increment();
        }
        return points;
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
    public void moveKing(Location start, Direction direction) {
        var newLocation = start.moveKing(direction);
        if(newLocation != start) {
            var piece = get(start);
            put(piece, newLocation);
            put(new Piece(), start);
        }
    }

    @Override
    public boolean equals(Object that){
        if(that instanceof Board t) {
            return this.ranks.equals(t.ranks);
        }
        return false;
    }

}