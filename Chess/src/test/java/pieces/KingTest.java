package pieces;

import java.util.List;

import static pieces.Color.*;

class KingTest extends AbstractPieceTest{
    @Override
    protected CreateByNew[] createByNew() {
        return new CreateByNew[]{
            new CreateByNew(new King(BLACK), BLACK, 'K'),
            new CreateByNew(new King(WHITE), WHITE, 'k'),
        };
    }

    @Override
    protected CreateByOf[] createByOf() {
        return new CreateByOf[]{
            new CreateByOf('K', King.class, BLACK),
            new CreateByOf('k', King.class, WHITE),
        };
    }
    @Override
    protected Piece createPiece() {
        return new King(WHITE);
    }

    @Override
    protected PossibleMoveTest createPossibleMoveTest() {
        return new PossibleMoveTest('K', List.of(
            new CurrentNext(
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . K . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1""",
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . K K K . . 5
                 . . . K . K . . 4
                 . . . K K K . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1"""
            ),
            new CurrentNext(
                """
                 K . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1""",
                """
                 . K . . . . . . 8
                 K K . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1"""
            ),
            new CurrentNext(
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . K 1""",
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . K K 2
                 . . . . . . K . 1"""
            )
        ));
    }

}