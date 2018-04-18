package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class TeleportConfirmPacket extends InboundPacket {

    private final int teleportID;

    public TeleportConfirmPacket(int teleportID) {
        this.teleportID = teleportID;
    }

    public int getTeleportID() {
        return teleportID;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.TELEPORT_CONFIRM;
    }
}
