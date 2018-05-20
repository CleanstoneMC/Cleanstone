package rocks.cleanstone.net.minecraft.login.event;

import java.util.Collection;
import java.util.UUID;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.player.UserProperty;

public class AsyncLoginSuccessEvent {

    private final Connection connection;
    private final UUID uuid;
    private final String name;
    private final Collection<UserProperty> userProperties;

    public AsyncLoginSuccessEvent(Connection connection, UUID uuid, String name,
                                  Collection<UserProperty> userProperties) {
        this.connection = connection;
        this.uuid = uuid;
        this.name = name;
        this.userProperties = userProperties;
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

    public Collection<UserProperty> getUserProperties() {
        return userProperties;
    }
}
