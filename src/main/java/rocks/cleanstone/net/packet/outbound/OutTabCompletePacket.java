package rocks.cleanstone.net.packet.outbound;

import java.util.List;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutTabCompletePacket implements Packet {

    private final List<String> matches;

    public OutTabCompletePacket(List<String> matches) {
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
