package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TeleportConfirmPacket implements Packet {

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
