package rocks.cleanstone.player.event;

import java.util.Collection;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.data.Text;
import rocks.cleanstone.player.PlayerID;
import rocks.cleanstone.player.UserProperty;

public class AsyncPlayerLoginEvent {

    private final Connection connection;
    private final PlayerID playerID;
    private final Collection<UserProperty> userProperties;
    private Text kickReason = Text.fromPlain("");
    private boolean cancelled = false;

    public AsyncPlayerLoginEvent(Connection connection, PlayerID playerID,
                                 Collection<UserProperty> userProperties) {
        this.connection = connection;
        this.playerID = playerID;
        this.userProperties = userProperties;
    }

    public Connection getConnection() {
        return connection;
    }

    public PlayerID getPlayerID() {
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
