package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.enums.AdvancementTabStatus;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AdvancementTabPacket implements Packet {

    private final AdvancementTabStatus advancementTabStatus;
    private final String tabID;

    public AdvancementTabPacket(int advancementTabStatus, String tabID) {
        this.advancementTabStatus = AdvancementTabStatus.fromStatusID(advancementTabStatus);
        this.tabID = tabID;
    }

    public AdvancementTabPacket(AdvancementTabStatus advancementTabStatus, String tabID) {
        this.advancementTabStatus = advancementTabStatus;
        this.tabID = tabID;
    }

    public AdvancementTabStatus getAdvancementTabStatus() {
        return advancementTabStatus;
    }

    public String getTabID() {
        return tabID;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.ADVANCEMENT_TAB;
    }
}
