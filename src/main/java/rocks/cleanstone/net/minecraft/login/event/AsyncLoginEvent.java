package rocks.cleanstone.net.minecraft.login.event;

import java.util.Collection;
import java.util.UUID;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.player.UserProperty;

public class AsyncLoginEvent extends CancellableEvent {

    private final Connection connection;
    private final UUID uuid;
    private final String name;
    private final Collection<UserProperty> userProperties;
    private Text kickReason = Text.of("");

    public AsyncLoginEvent(Connection connection, UUID uuid, String name, Collection<UserProperty> userProperties) {
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

    public Text getKickReason() {
        return kickReason;
    }

    public void setKickReason(Text kickReason) {
        this.kickReason = kickReason;
    }

    public Collection<UserProperty> getUserProperties() {
        return userProperties;
    }
}
