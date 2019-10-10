package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BookEditPacket implements Packet {


    public BookEditPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.BOOK_EDIT;
    }
}

