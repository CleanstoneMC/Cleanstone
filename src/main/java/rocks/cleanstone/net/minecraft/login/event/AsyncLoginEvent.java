package rocks.cleanstone.net.minecraft.login.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.player.UserProperty;

import java.util.Collection;
import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class AsyncLoginEvent extends CancellableEvent {
    Connection connection;
    UUID uuid;
    String name;
    Collection<UserProperty> userProperties;
    Text kickReason = Text.of("");

    public AsyncLoginEvent(Connection connection, UUID uuid, String name, Collection<UserProperty> userProperties) {
        this.connection = connection;
        this.uuid = uuid;
        this.name = name;
        this.userProperties = userProperties;
    }
}
