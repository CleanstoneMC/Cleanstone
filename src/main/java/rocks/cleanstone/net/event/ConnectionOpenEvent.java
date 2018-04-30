package rocks.cleanstone.net.event;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;

public class ConnectionOpenEvent {

    private final Connection connection;
    private final Networking networking;
    private boolean cancelled = false;

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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
