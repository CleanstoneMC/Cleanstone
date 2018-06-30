package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SpawnPositionPacket implements Packet {

    private final Position position;

    public SpawnPositionPacket(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SPAWN_POSITION;
    }
}
