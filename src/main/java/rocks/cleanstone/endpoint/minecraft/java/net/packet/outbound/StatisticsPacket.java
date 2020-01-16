package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.Map;

public class StatisticsPacket implements Packet {

    private final Map<String, Integer> statistics;

    public StatisticsPacket(Map<String, Integer> statistics) {
        this.statistics = statistics;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.STATISTICS;
    }
}
