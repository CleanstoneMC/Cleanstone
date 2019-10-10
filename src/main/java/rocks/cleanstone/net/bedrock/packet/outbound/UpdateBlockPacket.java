package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateBlockPacket implements Packet {

    private final BlockCoordinates coordinates;
    private final int blockRuntimeID;
    private final int blockPriority;
    private final int storage;

    public UpdateBlockPacket(BlockCoordinates coordinates, int blockRuntimeID, int blockPriority, int storage) {
        this.coordinates = coordinates;
        this.blockRuntimeID = blockRuntimeID;
        this.blockPriority = blockPriority;
        this.storage = storage;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public int getBlockRuntimeID() {
        return blockRuntimeID;
    }

    public int getBlockPriority() {
        return blockPriority;
    }

    public int getStorage() {
        return storage;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.UPDATE_BLOCK;
    }
}

