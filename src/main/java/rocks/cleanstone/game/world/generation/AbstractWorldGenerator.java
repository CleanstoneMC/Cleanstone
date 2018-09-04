package rocks.cleanstone.game.world.generation;

import java.util.HashMap;
import java.util.Map;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public abstract class AbstractWorldGenerator implements WorldGenerator {

    private final Dimension dimension;
    private final LevelType levelType;
    private final Map<WorldGenerationParameter, Double> generationParameters;

    public AbstractWorldGenerator(Dimension dimension, LevelType levelType) {
        this.dimension = dimension;
        this.levelType = levelType;
        generationParameters = new HashMap<>();
    }

    @Override
    public void setGenerationParameter(WorldGenerationParameter parameter, double value) {
        generationParameters.put(parameter, value);
    }

    @Override
    public double getGenerationParameter(WorldGenerationParameter parameter) {
        return generationParameters.get(parameter);
    }

    @Override
    public Map<WorldGenerationParameter, Double> getGenerationParameters() {
        return generationParameters;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
