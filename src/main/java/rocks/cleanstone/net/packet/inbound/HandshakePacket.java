package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class HandshakePacket implements Packet {

    private final int version;
    private final String address;
    private final int port;
    private final int state;

    public HandshakePacket(int version, String address, int port, int state) {
        this.version = version;
        this.address = address;
        this.port = port;
        this.state = state;
    }

    public int getVersion() {
        return version;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public int getState() {
        return state;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.HANDSHAKE;
    }
}
