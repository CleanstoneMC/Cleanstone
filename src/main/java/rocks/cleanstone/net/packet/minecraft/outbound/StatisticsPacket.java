package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

import java.util.Map;

public class StatisticsPacket extends OutboundPacket {

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
