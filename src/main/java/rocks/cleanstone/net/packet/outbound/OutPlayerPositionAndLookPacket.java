package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutPlayerPositionAndLookPacket implements Packet {

    private final RotatablePosition position;
    private final int flags;
    private final int teleportID;

    public OutPlayerPositionAndLookPacket(RotatablePosition position, int flags, int teleportID) {
        this.position=position;
        this.flags = flags;
        this.teleportID = teleportID;
    }

    public RotatablePosition getPosition() {
        return position;
    }

    public int getFlags() {
        return flags;
    }

    public int getTeleportID() {
        return teleportID;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.PLAYER_POSITION_AND_LOOK;
    }
}
