package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResourcePackDataInfoPacket implements Packet {

    private final String packageID;
    private final int maxChunkSize;
    private final int chunkCount;
    private final long compressedPackageSize;
    private final String hash;

    public ResourcePackDataInfoPacket(String packageID, int maxChunkSize, int chunkCount, long compressedPackageSize, String hash) {
        this.packageID = packageID;
        this.maxChunkSize = maxChunkSize;
        this.chunkCount = chunkCount;
        this.compressedPackageSize = compressedPackageSize;
        this.hash = hash;
    }

    public String getPackageID() {
        return packageID;
    }

    public int getMaxChunkSize() {
        return maxChunkSize;
    }

    public int getChunkCount() {
        return chunkCount;
    }

    public long getCompressedPackageSize() {
        return compressedPackageSize;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.RESOURCE_PACK_DATA_INFO;
    }
}

