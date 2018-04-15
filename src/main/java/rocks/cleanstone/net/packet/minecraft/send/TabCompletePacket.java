package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

import java.util.List;

public class TabCompletePacket extends SendPacket {

    private final List<String> matches;

    public TabCompletePacket(List<String> matches) {
        this.matches = matches;
    }

    public List<String> getMatches() {
        return matches;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.TAB_COMPLETE;
    }
}
