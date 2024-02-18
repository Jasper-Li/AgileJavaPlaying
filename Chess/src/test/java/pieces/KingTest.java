package pieces;

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

}