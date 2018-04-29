package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.data.Chat;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class DisconnectPacket implements Packet {

    private final Chat reason;

    public DisconnectPacket(Chat reason) {
        this.reason = reason;
    }

    public Chat getReason() {
        return reason;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DISCONNECT;
    }
}
