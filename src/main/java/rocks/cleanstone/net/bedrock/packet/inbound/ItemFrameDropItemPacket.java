package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ItemFrameDropItemPacket implements Packet {

    private final BlockCoordinates coordinates;

    public ItemFrameDropItemPacket(BlockCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.ITEM_FRAME_DROP_ITEM;
    }
}

