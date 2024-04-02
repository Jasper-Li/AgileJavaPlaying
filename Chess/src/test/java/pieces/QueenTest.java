package pieces;

import java.util.List;

import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

class QueenTest extends AbstractPieceTest{
    @Override
    protected CreateByNew[] createByNew() {
        return new CreateByNew[]{
            new CreateByNew(new Queen(BLACK), BLACK, 'Q'),
            new CreateByNew(new Queen(WHITE), WHITE, 'q'),
        };
    }

    @Override
    protected CreateByOf[] createByOf() {
        return new CreateByOf[]{
            new CreateByOf('Q', Queen.class, BLACK),
            new CreateByOf('q', Queen.class, WHITE),
        };
    }
    @Override
    protected Piece createPiece() {
        return new Queen(WHITE);
    }
    @Override
    protected PossibleMoveTest createPossibleMoveTest() {
        return new PossibleMoveTest('q', List.of(
            new CurrentNext(
                """
                 . . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . q . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1""",
                """
                 q . . . q . . . 8
                 . q . . q . . q 7
                 . . q . q . q . 6
                 . . . q q q . . 5
                 q q q q . q q q 4
                 . . . q q q . . 3
                 . . q . q . q . 2
                 . q . . q . . q 1"""
            ),
            new CurrentNext(
                """
                 q . . . . . . . 8
                 . . . . . . . . 7
                 . . . . . . . . 6
                 . . . . . . . . 5
                 . . . . . . . . 4
                 . . . . . . . . 3
                 . . . . . . . . 2
                 . . . . . . . . 1""",
                """
                 . q q q q q q q 8
                 q q . . . . . . 7
                 q . q . . . . . 6
                 q . . q . . . . 5
                 q . . . q . . . 4
                 q . . . . q . . 3
                 q . . . . . q . 2
                 q . . . . . . q 1"""
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
                 . . . . . . . q 1""",
                """
                 q . . . . . . q 8
                 . q . . . . . q 7
                 . . q . . . . q 6
                 . . . q . . . q 5
                 . . . . q . . q 4
                 . . . . . q . q 3
                 . . . . . . q q 2
                 q q q q q q q . 1"""
            )
        ));
    }
}
