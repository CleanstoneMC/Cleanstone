package rocks.cleanstone.net.event;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.player.Player;

public class PlayerOutboundPacketEvent<T extends Packet> extends CancellableEvent implements ResolvableTypeProvider {

    private final T packet;
    private final Connection connection;
    private final Player player;
    private final Networking networking;

    public PlayerOutboundPacketEvent(T packet, Connection connection, Player player,
                                     Networking networking) {
        this.packet = packet;
        this.connection = connection;
        this.player = player;
        this.networking = networking;
    }

    public T getPacket() {
        return packet;
    }

    public Connection getConnection() {
        return connection;
    }

    public Player getPlayer() {
        return player;
    }

    public Networking getNetworking() {
        return networking;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),
                ResolvableType.forInstance(packet));
    }
}