package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BookEditPacket implements Packet {


    public BookEditPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.BOOK_EDIT;
    }
}

