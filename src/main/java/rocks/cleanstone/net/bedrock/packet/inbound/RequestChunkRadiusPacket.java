package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class RequestChunkRadiusPacket implements Packet {

    private final int chunkRadius;

    public RequestChunkRadiusPacket(int chunkRadius) {
        this.chunkRadius = chunkRadius;
    }

    public int getChunkRadius() {
        return chunkRadius;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.REQUEST_CHUNK_RADIUS;
    }
}

