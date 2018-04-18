package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class EncryptionRequestPacket extends OutboundPacket {

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
