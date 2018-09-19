package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetScoreboardIdentityPacketPacket implements Packet {


    public SetScoreboardIdentityPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SET_SCOREBOARD_IDENTITY_PACKET;
    }
}

