package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateSoftEnumPacketPacket implements Packet {


    public UpdateSoftEnumPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.UPDATE_SOFT_ENUM_PACKET;
    }
}

