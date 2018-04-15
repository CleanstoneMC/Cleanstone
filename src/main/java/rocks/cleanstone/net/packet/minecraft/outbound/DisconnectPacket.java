package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class DisconnectPacket extends OutboundPacket {

    private final String reason;

    public DisconnectPacket(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DISCONNECT;
    }
}
