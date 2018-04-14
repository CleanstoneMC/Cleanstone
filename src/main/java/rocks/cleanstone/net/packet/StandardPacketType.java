package rocks.cleanstone.net.packet;

import rocks.cleanstone.net.packet.cleanstone.CleanstonePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;

public enum StandardPacketType implements PacketType {
    CLEANSTONE(0, CleanstonePacket.class), MINECRAFT(1, MinecraftPacket.class);

    private final int typeId;
    private final Class<? extends Packet> packetClass;

    StandardPacketType(int typeId, Class<? extends Packet> packetClass) {
        this.typeId = typeId;
        this.packetClass = packetClass;
    }

    public int getTypeId() {
        return typeId;
    }
}
