package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

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
