package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TransferPacket implements Packet {

    private final String serverAddress;
    private final short port;

    public TransferPacket(String serverAddress, short port) {
        this.serverAddress =  serverAddress;
        this.port =  port;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public short getPort() {
        return port;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.TRANSFER;
    }
}

