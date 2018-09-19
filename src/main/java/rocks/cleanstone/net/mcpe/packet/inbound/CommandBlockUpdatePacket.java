package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CommandBlockUpdatePacket implements Packet {


    public CommandBlockUpdatePacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.COMMAND_BLOCK_UPDATE;
    }
}

