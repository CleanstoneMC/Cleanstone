package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class DisconnectPacket extends SendPacket {

    private final String reason;

    public DisconnectPacket(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.DISCONNECT;
    }
}
