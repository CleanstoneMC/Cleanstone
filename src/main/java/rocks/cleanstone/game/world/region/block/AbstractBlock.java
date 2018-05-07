package rocks.cleanstone.game.world.region.block;

import rocks.cleanstone.game.Position;

public abstract class AbstractBlock implements Block {

    private final Position position;

    protected AbstractBlock(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
