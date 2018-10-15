package rocks.cleanstone.game.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.generation.WorldGenerator;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class SimpleWorldGeneratorManager implements WorldGeneratorManager {

    private final Map<String, WorldGenerator> worldGenerators;

    @Autowired
    public SimpleWorldGeneratorManager(List<WorldGenerator> worldGenerators) {
        this.worldGenerators = worldGenerators.stream()
                .collect(toMap(
                        WorldGenerator::getName,
                        identity()
                ));
    }

    @Override
    public WorldGenerator getWorldGenerator(String worldGeneratorName) {
        return worldGenerators.get(worldGeneratorName);
    }

    @Override
    public void registerWorldGenerator(WorldGenerator worldGenerator) {
        worldGenerators.put(worldGenerator.getName(), worldGenerator);
    }
}
