package rocks.cleanstone.game.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.generation.WorldGenerator;

import java.util.List;

@Component
public class SimpleWorldGeneratorManager implements WorldGeneratorManager {

    private List<? extends WorldGenerator> worldGenerators;

    @Autowired
    public SimpleWorldGeneratorManager(List<? extends WorldGenerator> worldGenerators) {
        this.worldGenerators = worldGenerators;
    }

    @Override
    public WorldGenerator getWorldGenerator(String worldGeneratorName) {
        return worldGenerators.stream().filter(worldGenerator -> worldGenerator.getName().equals(worldGeneratorName)).findFirst().orElse(null);
    }
}
