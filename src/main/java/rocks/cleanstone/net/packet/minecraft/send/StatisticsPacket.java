package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

import java.util.Map;

public class StatisticsPacket extends SendPacket {

    private final Map<String, Integer> statistics;

    public StatisticsPacket(Map<String, Integer> statistics) {
        this.statistics = statistics;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.STATISTICS;
    }
}
