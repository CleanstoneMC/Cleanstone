package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TransferPacket implements Packet {

    private final String serverAddress;
    private final short port;

    public TransferPacket(String serverAddress, short port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public short getPort() {
        return port;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.TRANSFER;
    }
}

