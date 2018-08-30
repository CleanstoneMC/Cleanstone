package rocks.cleanstone.net.event;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.player.Player;

public class PlayerInboundPacketEvent<T extends Packet> extends CancellableEvent implements ResolvableTypeProvider {

    private final Networking networking;
    private final T packet;
    private final Connection connection;
    private final Player player;

    public PlayerInboundPacketEvent(T packet, Connection connection, Player player,
                                    Networking networking) {
        this.packet = packet;
        this.connection = connection;
        this.networking = networking;
        this.player = player;
    }

    public Networking getNetworking() {
        return networking;
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

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),
                ResolvableType.forInstance(packet));
    }
}
