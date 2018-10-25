package rocks.cleanstone.net.minecraft.login.event;

import lombok.Value;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.player.UserProperty;

import java.util.Collection;
import java.util.UUID;

@Value
public class AsyncLoginSuccessEvent {
    Connection connection;
    UUID uuid;
    String name;
    Collection<UserProperty> userProperties;

    public AsyncLoginSuccessEvent(Connection connection, UUID uuid, String name,
                                  Collection<UserProperty> userProperties) {
        this.connection = connection;
        this.uuid = uuid;
        this.name = name;
        this.userProperties = userProperties;
    }
}
