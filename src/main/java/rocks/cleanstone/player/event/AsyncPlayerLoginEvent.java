package rocks.cleanstone.player.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.player.UserProperty;

import java.util.Collection;

@Value
@EqualsAndHashCode(callSuper = true)
public class AsyncPlayerLoginEvent extends CancellableEvent {
    Connection connection;
    Identity playerID;
    Collection<UserProperty> userProperties;
    Text kickReason = Text.of("");

    public AsyncPlayerLoginEvent(Connection connection, Identity playerID,
                                 Collection<UserProperty> userProperties) {
        this.connection = connection;
        this.playerID = playerID;
        this.userProperties = userProperties;
    }
}
