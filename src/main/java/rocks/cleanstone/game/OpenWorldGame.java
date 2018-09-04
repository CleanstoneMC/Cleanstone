package rocks.cleanstone.game;

import org.springframework.context.Lifecycle;
import rocks.cleanstone.game.world.World;

public interface OpenWorldGame extends Lifecycle {

    World getFirstSpawnWorld();
}
