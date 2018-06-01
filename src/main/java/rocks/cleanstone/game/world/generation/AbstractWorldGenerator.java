package rocks.cleanstone.game.world.generation;

import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public abstract class AbstractWorldGenerator implements WorldGenerator {

    private final Dimension dimension;
    private final LevelType levelType;
    private final int seed;

    public AbstractWorldGenerator(Dimension dimension, LevelType levelType, int seed) {
        this.dimension = dimension;
        this.levelType = levelType;
        this.seed = seed;
    }

    public int getSeed() {
        return seed;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
