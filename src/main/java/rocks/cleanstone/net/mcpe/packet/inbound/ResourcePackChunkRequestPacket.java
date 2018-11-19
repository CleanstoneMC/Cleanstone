package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResourcePackChunkRequestPacket implements Packet {

    private final String packageID;
    private final int chunkIndex;

    public ResourcePackChunkRequestPacket(String packageID, int chunkIndex) {
        this.packageID = packageID;
        this.chunkIndex = chunkIndex;
    }

    public String getPackageID() {
        return packageID;
    }

    public int getChunkIndex() {
        return chunkIndex;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.RESOURCE_PACK_CHUNK_REQUEST;
    }
}

