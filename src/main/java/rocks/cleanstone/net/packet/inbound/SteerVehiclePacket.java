package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.enums.SteerVehicleFlag;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SteerVehiclePacket implements Packet {

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
