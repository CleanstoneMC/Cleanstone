package rocks.cleanstone.core.player;

import rocks.cleanstone.net.PlayerConnection;

public class OnlinePlayer {

    private final PlayerId id;
    private final PlayerConnection connection;

    public OnlinePlayer(PlayerId id, PlayerConnection connection) {
        this.id = id;
        this.connection = connection;
    }

    public PlayerId getId() {
        return id;
    }

    public PlayerConnection getConnection() {
        return connection;
    }
}
