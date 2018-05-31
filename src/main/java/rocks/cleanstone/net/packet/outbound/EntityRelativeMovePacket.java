package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityRelativeMovePacket implements Packet {

    private final int entityID;
    private final short deltaX;
    private final short deltaY;
    private final short deltaZ;
    private final boolean onGround;


    public EntityRelativeMovePacket(int entityID, short deltaX, short deltaY, short deltaZ, boolean onGround) {
        this.entityID = entityID;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.onGround = onGround;
    }

    public int getEntityID() {
        return entityID;
    }

    public short getDeltaX() {
        return deltaX;
    }

    public short getDeltaY() {
        return deltaY;
    }

    public short getDeltaZ() {
        return deltaZ;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_RELATIVE_MOVE;
    }
}
