package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ItemFrameDropItemPacket implements Packet {

    private final BlockCoordinates coordinates;

    public ItemFrameDropItemPacket(BlockCoordinates coordinates) {
        this.coordinates =  coordinates;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.ITEM_FRAME_DROP_ITEM;
    }
}

