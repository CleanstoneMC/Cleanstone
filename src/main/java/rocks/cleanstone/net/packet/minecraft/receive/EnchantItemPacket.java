package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class EnchantItemPacket extends ReceivePacket {

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
        return MinecraftReceivePacketType.ENCHANT_ITEM;
    }
}
