package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TimeUpdatePacket implements Packet {

    private final long worldAge;
    private final long timeOfDay;

    public TimeUpdatePacket(long worldAge, long timeOfDay) {
        this.worldAge = worldAge;
        this.timeOfDay = timeOfDay;
    }

    public long getWorldAge() {
        return worldAge;
    }

    public long getTimeOfDay() {
        return timeOfDay;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.TIME_UPDATE;
    }
}
