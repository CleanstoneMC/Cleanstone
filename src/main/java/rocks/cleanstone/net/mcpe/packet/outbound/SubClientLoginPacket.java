package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SubClientLoginPacket implements Packet {


    public SubClientLoginPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SUB_CLIENT_LOGIN;
    }
}

