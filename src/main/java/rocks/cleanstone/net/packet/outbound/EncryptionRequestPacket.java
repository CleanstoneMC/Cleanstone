package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EncryptionRequestPacket implements Packet {

    private String serverID;

    private byte[] publicKey, verifyToken;

    public EncryptionRequestPacket(String serverID, byte[] publicKey, byte[] verifyToken) {
        this.serverID = serverID;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    public String getServerID() {
        return serverID;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENCRYPTION_REQUEST;
    }
}
