package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class StructureBlockUpdatePacket implements Packet {


    public StructureBlockUpdatePacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.STRUCTURE_BLOCK_UPDATE;
    }
}

