package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.StandardPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;

public class EnchantItemPacket implements MinecraftPacket {

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
        return StandardPacketType.MINECRAFT;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.RECEIVE;
    }
}
