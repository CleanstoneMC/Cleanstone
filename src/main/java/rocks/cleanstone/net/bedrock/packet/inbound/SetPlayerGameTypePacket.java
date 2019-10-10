package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetPlayerGameTypePacket implements Packet {

    private final int gamemode;

    public SetPlayerGameTypePacket(int gamemode) {
        this.gamemode = gamemode;
    }

    public int getGamemode() {
        return gamemode;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.SET_PLAYER_GAME_TYPE;
    }
}

