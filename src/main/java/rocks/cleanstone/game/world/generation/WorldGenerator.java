package rocks.cleanstone.game.world.generation;

import com.google.common.collect.Table;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

import java.util.Map;

public interface WorldGenerator {

    Chunk generateChunk(int seed, ChunkCoords coords);

    RotatablePosition getFirstSpawnPosition(int seed);

    void setGenerationParameter(WorldGenerationParameter parameter, double value);

    double getGenerationParameter(WorldGenerationParameter parameter);

    void setFractalGenerationParameter(FractalWorldGenerationParameter parameter, int octave, double value);

    double getFractalGenerationParameter(FractalWorldGenerationParameter parameter, int octave);

    Map<WorldGenerationParameter, Double> getGenerationParameters();

    Table<FractalWorldGenerationParameter, Integer, Double> getFractalGenerationParameters();

    String getName();
}
