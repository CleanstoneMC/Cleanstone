package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.StandardPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;

public class TeleportConfirmPacket implements MinecraftPacket {

    private final int teleportID;

    public TeleportConfirmPacket(int teleportID) {
        this.teleportID = teleportID;
    }

    public int getTeleportID() {
        return teleportID;
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
