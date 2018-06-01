package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EncryptionResponsePacket implements Packet {

    private final byte[] sharedSecret;
    private final byte[] verifyToken;

    public EncryptionResponsePacket(byte[] sharedSecret, byte[] verifyToken) {
        this.sharedSecret = sharedSecret;
        this.verifyToken = verifyToken;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.ENCRYPTION_RESPONSE;
    }
}
