package pieces;

import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

class KnightTest extends AbstractPieceTest{
    @Override
    protected CreateByNew[] createByNew() {
        return new CreateByNew[]{
            new CreateByNew(new Knight(BLACK), BLACK, 'N'),
            new CreateByNew(new Knight(WHITE), WHITE, 'n'),
        };
    }

    @Override
    protected CreateByOf[] createByOf() {
        return new CreateByOf[]{
            new CreateByOf('N', Knight.class, BLACK),
            new CreateByOf('n', Knight.class, WHITE),
        };
    }
    @Override
    protected Piece createPiece() {
        return new Knight(WHITE);
    }

    @Override
    protected PossibleMoveTest createPossibleMoveTest() {
        return new PossibleMoveTest();
    }
}