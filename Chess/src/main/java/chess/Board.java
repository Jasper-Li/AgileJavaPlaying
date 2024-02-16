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
    private final List<Rank> ranks = new ArrayList<Rank>(GRIDS_COUNT_PER_LINE);
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
        for(int i = 0; i < GRIDS_COUNT_PER_LINE; ++i) {
            final var rank = ranks.get(i);
            switch (rankIndex) {
                case RankIndex.R1-> {
                   rank.set(Color.WHITE, new BackRankArrangement());
                }
                case RankIndex.R2-> {
                    rank.set(Color.WHITE, new SecondRankArrangement());
                }
                case RankIndex.R7-> {
                    rank.set(Color.BLACK, new SecondRankArrangement());
                }
                case RankIndex.R8-> {
                    rank.set(Color.BLACK, new BackRankArrangement());
                }
                default -> rank.set(Rank.BLANK_REPRESENTATION);
            };
            rankIndex = rankIndex.increment();
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
            pieces.append(some);
        }
        return pieces;
    }


    public void put(Piece piece, Location location) {
        final var column = location.column();
        final var rank = location.rank();
        getRank(rank).put(column, piece);
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

    @Override
    public boolean equals(Object that){
        if(that instanceof Board t) {
            return this.ranks.equals(t.ranks);
        }
        return false;
    }

    static Set<Location> getKingPossibleMoves(Location current) {
        final Set<Location> locations = new HashSet<>();
        final int[] validSteps={-1, 0, 1};
        for(int i=0; i < validSteps.length; ++i) {
            for (int j = 0; j < validSteps.length; ++j) {
                final var optionalAfter = current.move(validSteps[i], validSteps[j]);
                if(optionalAfter.isEmpty()) continue;
                locations.add(optionalAfter.get());
            }
        }
        return locations;
    }

}