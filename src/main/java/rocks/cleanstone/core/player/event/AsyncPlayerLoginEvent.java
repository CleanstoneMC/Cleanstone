package rocks.cleanstone.core.player.event;

import rocks.cleanstone.core.player.PlayerID;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.data.Text;

public class AsyncPlayerLoginEvent {

    private final Connection connection;
    private final PlayerID playerID;
    private Text kickReason = Text.fromPlain("");
    private boolean cancelled = false;

    public AsyncPlayerLoginEvent(Connection connection, PlayerID playerID) {
        this.connection = connection;
        this.playerID = playerID;
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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
