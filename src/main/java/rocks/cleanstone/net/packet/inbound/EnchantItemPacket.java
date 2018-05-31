package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EnchantItemPacket implements Packet {

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
