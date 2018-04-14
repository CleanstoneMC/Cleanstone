package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class TeleportConfirmPacket extends ReceivePacket {

    private final int teleportID;

    public TeleportConfirmPacket(int teleportID) {
        this.teleportID = teleportID;
    }

    public int getTeleportID() {
        return teleportID;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.TELEPORT_CONFIRM;
    }
}
