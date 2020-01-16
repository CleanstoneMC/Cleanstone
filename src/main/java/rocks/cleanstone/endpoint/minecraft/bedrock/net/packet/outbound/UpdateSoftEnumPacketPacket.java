package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
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

