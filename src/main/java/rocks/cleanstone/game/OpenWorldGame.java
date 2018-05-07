package rocks.cleanstone.game;

import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.game.world.WorldManager;

public interface OpenWorldGame {
    WorldManager getWorldManager();

    CleanstoneMainServer getServer();
}
