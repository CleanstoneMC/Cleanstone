package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class SteerBoatPacket extends ReceivePacket {

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
        return MinecraftReceivePacketType.STEER_BOAT;
    }
}
