package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SubClientLoginPacket implements Packet {


    public SubClientLoginPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SUB_CLIENT_LOGIN;
    }
}

