package rocks.cleanstone.game.world.region.block;

import rocks.cleanstone.game.Position;

public abstract class Block {

    private final Position position;

    protected Block(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
