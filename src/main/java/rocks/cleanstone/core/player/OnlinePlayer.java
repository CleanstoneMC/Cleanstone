package rocks.cleanstone.core.player;

import rocks.cleanstone.net.Connection;

public class OnlinePlayer extends AbstractPlayer {

    private final Connection connection;

    public OnlinePlayer(PlayerId id, Connection connection) {
        super(id);

        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
