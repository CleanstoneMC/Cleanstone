package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.AdvancementTabStatus;

public class AdvancementTabPacket {

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
