package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.enums.SteerVehicleFlag;

public class SteerVehiclePacket extends ReceivePacket {

    private final float sideways;
    private final float forward;
    private final SteerVehicleFlag steerVehicleFlag;

    public SteerVehiclePacket(float sideways, float forward, byte steerVehicleFlag) {
        this.sideways = sideways;
        this.forward = forward;
        this.steerVehicleFlag = SteerVehicleFlag.fromBitMask(steerVehicleFlag);
    }

    public SteerVehiclePacket(float sideways, float forward, SteerVehicleFlag steerVehicleFlag) {
        this.sideways = sideways;
        this.forward = forward;
        this.steerVehicleFlag = steerVehicleFlag;
    }

    public float getSideways() {
        return sideways;
    }

    public float getForward() {
        return forward;
    }

    public SteerVehicleFlag getSteerVehicleFlag() {
        return steerVehicleFlag;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.STEER_VEHICLE;
    }
}
