package pieces;

import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

class BishopTest extends AbstractPieceTest{
    @Override
    protected CreateByNew[] createByNew() {
        return new CreateByNew[]{
            new CreateByNew(new Bishop(BLACK), BLACK, 'B'),
            new CreateByNew(new Bishop(WHITE), WHITE, 'b'),
        };
    }

    @Override
    protected CreateByOf[] createByOf() {
        return new CreateByOf[]{
            new CreateByOf('B', Bishop.class, BLACK),
            new CreateByOf('b', Bishop.class, WHITE),
        };
    }

    @Override
    protected Piece createPiece() {
        return new Bishop(WHITE);
    }
}