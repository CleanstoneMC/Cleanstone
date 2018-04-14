package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.enums.AdvancementTabStatus;

public class AdvancementTabPacket extends ReceivePacket {

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
        return MinecraftReceivePacketType.ADVANCEMENT_TAB;
    }
}
