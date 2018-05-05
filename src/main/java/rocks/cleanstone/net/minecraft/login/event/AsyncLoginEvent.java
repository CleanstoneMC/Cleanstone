package rocks.cleanstone.net.minecraft.login.event;

import java.util.UUID;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.data.Text;

public class AsyncLoginEvent {

    private final Connection connection;
    private final UUID uuid;
    private final String name;
    private Text kickReason = Text.fromPlain("");
    private boolean cancelled = false;

    public AsyncLoginEvent(Connection connection, UUID uuid, String name) {
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
