package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateBlockPacket implements Packet {

    private final BlockCoordinates coordinates;
    private final int blockRuntimeID;
    private final int blockPriority;
    private final int storage;

    public UpdateBlockPacket(BlockCoordinates coordinates, int blockRuntimeID, int blockPriority, int storage) {
        this.coordinates =  coordinates;
        this.blockRuntimeID =  blockRuntimeID;
        this.blockPriority =  blockPriority;
        this.storage =  storage;
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
        return MCPEOutboundPacketType.UPDATE_BLOCK;
    }
}

