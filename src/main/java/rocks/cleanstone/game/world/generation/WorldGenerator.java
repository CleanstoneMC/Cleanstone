package rocks.cleanstone.game.world.generation;

import java.util.Map;
import rocks.cleanstone.game.world.chunk.Chunk;

public interface WorldGenerator {

    Chunk generateChunk(int seed, int x, int y);

    int getHeightAt(int seed, int x, int y);

    void setGenerationParameter(WorldGenerationParameter parameter, double value);

    double getGenerationParameter(WorldGenerationParameter parameter);

    Map<WorldGenerationParameter, Double> getGenerationParameters();

    String getName();
}
