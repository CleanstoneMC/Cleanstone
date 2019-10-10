package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetSpawnPositionPacket implements Packet {

    private final int spawnType;
    private final BlockCoordinates coordinates;
    private final boolean forced;

    public SetSpawnPositionPacket(int spawnType, BlockCoordinates coordinates, boolean forced) {
        this.spawnType = spawnType;
        this.coordinates = coordinates;
        this.forced = forced;
    }

    public int getSpawnType() {
        return spawnType;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public boolean getForced() {
        return forced;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_SPAWN_POSITION;
    }
}

