package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SteerBoatPacket implements Packet {

    private final boolean rightPaddleTurning;
    private final boolean leftPaddleTurning;

    public SteerBoatPacket(boolean rightPaddleTurning, boolean leftPaddleTurning) {
        this.rightPaddleTurning = rightPaddleTurning;
        this.leftPaddleTurning = leftPaddleTurning;
    }

    public boolean isRightPaddleTurning() {
        return rightPaddleTurning;
    }

    public boolean isLeftPaddleTurning() {
        return leftPaddleTurning;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.STEER_BOAT;
    }
}
