package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerActionPacket implements Packet {

    private final long runtimeEntityID;
    private final int actionID;
    private final BlockCoordinates coordinates;
    private final int face;

    public PlayerActionPacket(long runtimeEntityID, int actionID, BlockCoordinates coordinates, int face) {
        this.runtimeEntityID = runtimeEntityID;
        this.actionID = actionID;
        this.coordinates = coordinates;
        this.face = face;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public int getActionID() {
        return actionID;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public int getFace() {
        return face;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.PLAYER_ACTION;
    }
}

