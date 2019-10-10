package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
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

