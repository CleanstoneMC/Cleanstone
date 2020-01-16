package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class StructureBlockUpdatePacket implements Packet {


    public StructureBlockUpdatePacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.STRUCTURE_BLOCK_UPDATE;
    }
}

