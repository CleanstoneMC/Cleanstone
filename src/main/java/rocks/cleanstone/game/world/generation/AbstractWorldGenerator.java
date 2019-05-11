package rocks.cleanstone.game.world.generation;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashMap;
import java.util.Map;

import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;

public abstract class AbstractWorldGenerator implements WorldGenerator {

    private final Dimension dimension;
    private final LevelType levelType;
    private final Map<WorldGenerationParameter, Double> generationParameters;
    private final Table<FractalWorldGenerationParameter, Integer, Double> fractalGenerationParameters;

    public AbstractWorldGenerator(Dimension dimension, LevelType levelType) {
        this.dimension = dimension;
        this.levelType = levelType;
        generationParameters = new HashMap<>();
        fractalGenerationParameters = HashBasedTable.create();
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
    public void setFractalGenerationParameter(FractalWorldGenerationParameter parameter, int octave, double value) {
        fractalGenerationParameters.put(parameter, octave, value);
    }

    @Override
    public double getFractalGenerationParameter(FractalWorldGenerationParameter parameter, int octave) {
        return fractalGenerationParameters.get(parameter, octave);
    }

    @Override
    public Map<WorldGenerationParameter, Double> getGenerationParameters() {
        return generationParameters;
    }

    @Override
    public Table<FractalWorldGenerationParameter, Integer, Double> getFractalGenerationParameters() {
        return fractalGenerationParameters;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
