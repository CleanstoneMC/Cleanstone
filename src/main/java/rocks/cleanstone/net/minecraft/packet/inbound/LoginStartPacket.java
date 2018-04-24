package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;

public class LoginStartPacket implements Packet {

    private final String playerName;

    public LoginStartPacket(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.LOGIN_START;
    }
}
