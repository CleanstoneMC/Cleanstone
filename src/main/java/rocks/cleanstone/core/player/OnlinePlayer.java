package rocks.cleanstone.core.player;

import rocks.cleanstone.net.PlayerConnection;

import java.util.UUID;

public class OnlinePlayer implements PlayerId {

    private final PlayerId id;
    private final PlayerConnection connection;

    public OnlinePlayer(PlayerId id, PlayerConnection connection) {
        this.id = id;
        this.connection = connection;
    }

    public PlayerConnection getConnection() {
        return connection;
    }

    @Override
    public String getName() {
        return id.getName();
    }

    @Override
    public UUID getUUID() {
        return id.getUUID();
    }
}
