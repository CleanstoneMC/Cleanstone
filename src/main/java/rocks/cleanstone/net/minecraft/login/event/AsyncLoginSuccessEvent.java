package rocks.cleanstone.net.minecraft.login.event;

import java.util.UUID;

import rocks.cleanstone.net.Connection;

public class AsyncLoginSuccessEvent {

    private final Connection connection;
    private final UUID uuid;
    private final String name;

    public AsyncLoginSuccessEvent(Connection connection, UUID uuid, String name) {
        this.connection = connection;
        this.uuid = uuid;
        this.name = name;
    }

    public Connection getConnection() {
        return connection;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
