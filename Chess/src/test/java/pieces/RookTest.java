package pieces;

import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

class RookTest extends AbstractPieceTest{
    @Override
    protected CreateByNew[] createByNew() {
        return new CreateByNew[]{
            new CreateByNew(new Rook(BLACK), BLACK, 'R'),
            new CreateByNew(new Rook(WHITE), WHITE, 'r'),
        };
    }

    @Override
    protected CreateByOf[] createByOf() {
        return new CreateByOf[]{
            new CreateByOf('R', Rook.class, BLACK),
            new CreateByOf('r', Rook.class, WHITE),
        };
    }
    protected Piece createPiece() {
        return new Rook(WHITE);
    }
    @Override
    protected PossibleMoveTest createPossibleMoveTest() {
        return new PossibleMoveTest();
    }
}