package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResourcePackChunkDataPacket implements Packet {

    private final String packageID;
    private final int chunkIndex;
    private final long progress;
    private final int length;
    private final byte[] payload;

    public ResourcePackChunkDataPacket(String packageID, int chunkIndex, long progress, int length, byte[] payload) {
        this.packageID = packageID;
        this.chunkIndex = chunkIndex;
        this.progress = progress;
        this.length = length;
        this.payload = payload;
    }

    public String getPackageID() {
        return packageID;
    }

    public int getChunkIndex() {
        return chunkIndex;
    }

    public long getProgress() {
        return progress;
    }

    public int getLength() {
        return length;
    }

    public byte[] getPayload() {
        return payload;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.RESOURCE_PACK_CHUNK_DATA;
    }
}

