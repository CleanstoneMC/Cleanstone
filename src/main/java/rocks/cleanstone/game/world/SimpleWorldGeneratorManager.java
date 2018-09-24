package rocks.cleanstone.game.world;

import java.util.List;
import java.util.Map;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.generation.WorldGenerator;

@Component
public class SimpleWorldGeneratorManager implements WorldGeneratorManager {

    private Map<String, ? extends WorldGenerator> worldGenerators;

    @Autowired
    public SimpleWorldGeneratorManager(List<? extends WorldGenerator> worldGenerators) {
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
}
