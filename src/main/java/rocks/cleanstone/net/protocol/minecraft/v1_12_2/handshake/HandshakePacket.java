package rocks.cleanstone.net.protocol.minecraft.v1_12_2.handshake;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.StandardPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;

public class HandshakePacket implements MinecraftPacket {

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
        return StandardPacketType.MINECRAFT;
    }
}
