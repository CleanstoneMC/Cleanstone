package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class SetCooldownPacket implements Packet {

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
