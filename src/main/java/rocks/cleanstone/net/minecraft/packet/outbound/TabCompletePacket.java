package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;

import java.util.List;

public class TabCompletePacket implements Packet {

    private final List<String> matches;

    public TabCompletePacket(List<String> matches) {
        this.matches = matches;
    }

    public List<String> getMatches() {
        return matches;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.TAB_COMPLETE;
    }
}
