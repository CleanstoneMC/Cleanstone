package rocks.cleanstone.player.event;

import java.util.Collection;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.player.UserProperty;

public class AsyncPlayerLoginEvent {

    private final Connection connection;
    private final Identity playerID;
    private final Collection<UserProperty> userProperties;
    private Text kickReason = Text.of("");
    private boolean cancelled = false;

    public AsyncPlayerLoginEvent(Connection connection, Identity playerID,
                                 Collection<UserProperty> userProperties) {
        this.connection = connection;
        this.playerID = playerID;
        this.userProperties = userProperties;
    }

    public Connection getConnection() {
        return connection;
    }

    public Identity getPlayerID() {
        return playerID;
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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
