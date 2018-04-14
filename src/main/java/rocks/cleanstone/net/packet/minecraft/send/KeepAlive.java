package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class KeepAlive extends SendPacket {

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.HANDSHAKE;
    }
}
