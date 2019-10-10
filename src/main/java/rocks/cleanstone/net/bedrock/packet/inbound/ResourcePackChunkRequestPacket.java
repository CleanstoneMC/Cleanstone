package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
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
        return BedrockInboundPacketType.RESOURCE_PACK_CHUNK_REQUEST;
    }
}

