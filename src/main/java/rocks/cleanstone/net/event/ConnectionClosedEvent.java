package rocks.cleanstone.net.event;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;

public class ConnectionClosedEvent {

    private final Connection connection;
    private final Networking networking;

    public ConnectionClosedEvent(Connection connection, Networking networking) {
        this.connection = connection;
        this.networking = networking;
    }

    public Connection getConnection() {
        return connection;
    }

    public Networking getNetworking() {
        return networking;
    }
}
