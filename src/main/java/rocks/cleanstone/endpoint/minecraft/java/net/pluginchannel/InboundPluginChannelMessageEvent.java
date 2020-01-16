package rocks.cleanstone.endpoint.minecraft.java.net.pluginchannel;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.player.Player;

public class InboundPluginChannelMessageEvent<T extends PluginChannel.PluginMessage> extends CancellableEvent implements ResolvableTypeProvider {

    private final Networking networking;
    private final Connection connection;
    private final Player player;
    private final String channel;
    private final T message;

    public InboundPluginChannelMessageEvent(Networking networking, Connection connection, Player player, String channel, T message) {
        this.networking = networking;
        this.connection = connection;
        this.player = player;
        this.channel = channel;
        this.message = message;
    }

    public Networking getNetworking() {
        return networking;
    }

    public Connection getConnection() {
        return connection;
    }

    public Player getPlayer() {
        return player;
    }

    public String getChannel() {
        return channel;
    }

    public T getMessage() {
        return message;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),
                ResolvableType.forInstance(message));
    }
}
