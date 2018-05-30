package rocks.cleanstone.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.game.world.WorldManager;

public class SimpleOpenWorldGame implements OpenWorldGame {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SimpleOpenWorldGame(CleanstoneMainServer server, WorldManager worldManager) {
    }

}
