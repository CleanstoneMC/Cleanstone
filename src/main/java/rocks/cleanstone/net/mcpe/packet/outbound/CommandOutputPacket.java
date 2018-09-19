package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CommandOutputPacket implements Packet {


    public CommandOutputPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.COMMAND_OUTPUT;
    }
}

