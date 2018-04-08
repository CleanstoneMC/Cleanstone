package rocks.cleanstone.core.player;

import rocks.cleanstone.net.PlayerConnection;

public class OnlinePlayer extends AbstractPlayer {

    private final PlayerConnection connection;

    public OnlinePlayer(PlayerId id, PlayerConnection connection) {
        super(id);

        this.connection = connection;
    }

    public PlayerConnection getConnection() {
        return connection;
    }
}
