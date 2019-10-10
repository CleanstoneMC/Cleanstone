package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetDefaultGameTypePacket implements Packet {

    private final int gamemode;

    public SetDefaultGameTypePacket(int gamemode) {
        this.gamemode = gamemode;
    }

    public int getGamemode() {
        return gamemode;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_DEFAULT_GAME_TYPE;
    }
}

