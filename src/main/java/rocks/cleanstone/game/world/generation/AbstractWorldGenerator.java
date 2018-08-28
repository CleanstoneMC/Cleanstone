package rocks.cleanstone.game.world.generation;

import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public abstract class AbstractWorldGenerator implements WorldGenerator {

    private final Dimension dimension;
    private final LevelType levelType;

    public AbstractWorldGenerator(Dimension dimension, LevelType levelType) {
        this.dimension = dimension;
        this.levelType = levelType;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
