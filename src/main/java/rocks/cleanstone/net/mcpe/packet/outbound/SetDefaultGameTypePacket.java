package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetDefaultGameTypePacket implements Packet {

    private final int gamemode;

    public SetDefaultGameTypePacket(int gamemode) {
        this.gamemode =  gamemode;
    }

    public int getGamemode() {
        return gamemode;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SET_DEFAULT_GAME_TYPE;
    }
}

