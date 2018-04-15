package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class SetCooldownPacket extends SendPacket {

    private final int itemID;
    private final int cooldownTicks;

    public SetCooldownPacket(int itemID, int cooldownTicks) {
        this.itemID = itemID;
        this.cooldownTicks = cooldownTicks;
    }

    public int getItemID() {
        return itemID;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.SET_COOLDOWN;
    }
}
