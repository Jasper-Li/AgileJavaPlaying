package pieces;

import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

class PawnTest extends AbstractPieceTest{
    @Override
    protected CreateByNew[] createByNew() {
        return new CreateByNew[]{
            new CreateByNew(new Pawn(BLACK), BLACK, 'P'),
            new CreateByNew(new Pawn(WHITE), WHITE, 'p'),
        };
    }

    @Override
    protected CreateByOf[] createByOf() {
        return new CreateByOf[]{
            new CreateByOf('P', Pawn.class, BLACK),
            new CreateByOf('p', Pawn.class, WHITE),
        };
    }
    @Override
    protected Piece createPiece() {
        return new Pawn(WHITE);
    }
}