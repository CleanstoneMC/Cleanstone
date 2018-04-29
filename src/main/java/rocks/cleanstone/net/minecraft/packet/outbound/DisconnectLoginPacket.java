package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.data.Chat;
import rocks.cleanstone.net.packet.PacketType;

public class DisconnectLoginPacket extends DisconnectPacket {

    public DisconnectLoginPacket(Chat reason) {
        super(reason);
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DISCONNECT_LOGIN;
    }
}
