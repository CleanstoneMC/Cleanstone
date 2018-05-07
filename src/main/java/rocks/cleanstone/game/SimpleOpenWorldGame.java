package rocks.cleanstone.game;

import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.game.world.SimpleWorldManager;
import rocks.cleanstone.game.world.WorldManager;

public class SimpleOpenWorldGame implements OpenWorldGame {

    private final WorldManager worldManager;
    private final CleanstoneMainServer server;

    public SimpleOpenWorldGame(CleanstoneMainServer server) {
        worldManager = new SimpleWorldManager(this);
        this.server = server;
    }

    @Override
    public WorldManager getWorldManager() {
        return worldManager;
    }

    public CleanstoneMainServer getServer() {
        return server;
    }
}
