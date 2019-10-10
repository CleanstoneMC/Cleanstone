package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetScoreboardIdentityPacketPacket implements Packet {


    public SetScoreboardIdentityPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_SCOREBOARD_IDENTITY_PACKET;
    }
}

