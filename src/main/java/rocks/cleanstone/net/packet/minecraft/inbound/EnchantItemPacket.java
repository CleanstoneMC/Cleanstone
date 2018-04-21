package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class EnchantItemPacket {

    private final byte windowID;
    private final byte enchantment;

    public EnchantItemPacket(byte windowID, byte enchantment) {
        this.windowID = windowID;
        this.enchantment = enchantment;
    }

    public byte getWindowID() {
        return windowID;
    }

    public byte getEnchantment() {
        return enchantment;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.ENCHANT_ITEM;
    }
}
