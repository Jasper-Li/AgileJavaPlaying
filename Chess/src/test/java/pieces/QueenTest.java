package pieces;

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
}