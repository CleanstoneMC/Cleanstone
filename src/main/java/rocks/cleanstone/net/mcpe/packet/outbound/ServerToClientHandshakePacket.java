package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ServerToClientHandshakePacket implements Packet {

    private final String token;

    public ServerToClientHandshakePacket(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SERVER_TO_CLIENT_HANDSHAKE;
    }
}

