package rocks.cleanstone.net.event;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;

public class ConnectionOpenEvent extends CancellableEvent {

    private final Connection connection;
    private final Networking networking;

    public ConnectionOpenEvent(Connection connection, Networking networking) {
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
