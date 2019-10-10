package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockEventPacket implements Packet {

    private final BlockCoordinates coordinates;
    private final int case1;
    private final int case2;

    public BlockEventPacket(BlockCoordinates coordinates, int case1, int case2) {
        this.coordinates = coordinates;
        this.case1 = case1;
        this.case2 = case2;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public int getCase1() {
        return case1;
    }

    public int getCase2() {
        return case2;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.BLOCK_EVENT;
    }
}

