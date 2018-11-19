package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InitiateWebSocketConnectionPacket implements Packet {

    private final String server;

    public InitiateWebSocketConnectionPacket(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.INITIATE_WEB_SOCKET_CONNECTION;
    }
}

