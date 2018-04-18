package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class SetCooldownPacket extends OutboundPacket {

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
        return MinecraftOutboundPacketType.SET_COOLDOWN;
    }
}
