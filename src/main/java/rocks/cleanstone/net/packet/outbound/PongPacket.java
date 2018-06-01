package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PongPacket implements Packet {

    /**
     * Must be the same as sent by the client in PingPacket
     */
    private long payload;

    public PongPacket(long payload) {
        this.payload = payload;
    }

    public long getPayload() {
        return payload;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.PONG;
    }
}
