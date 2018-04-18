package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.SteerVehicleFlag;

public class SteerVehiclePacket extends InboundPacket {

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
        return MinecraftInboundPacketType.STEER_VEHICLE;
    }
}
