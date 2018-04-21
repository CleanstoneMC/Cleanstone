package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class LoginStartPacket {

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
