package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class PongPacket extends OutboundPacket {

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
