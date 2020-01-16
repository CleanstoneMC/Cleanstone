package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
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

