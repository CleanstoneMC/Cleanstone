package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ClientToServerHandshakePacket implements Packet {


    public ClientToServerHandshakePacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.CLIENT_TO_SERVER_HANDSHAKE;
    }
}

