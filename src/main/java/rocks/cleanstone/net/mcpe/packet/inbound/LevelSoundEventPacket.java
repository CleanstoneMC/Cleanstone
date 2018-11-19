package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class LevelSoundEventPacket implements Packet {

    private final byte soundID;
    private final Vector position;
    private final int blockId;
    private final int entityType;
    private final boolean isBabyMob;
    private final boolean isGlobal;

    public LevelSoundEventPacket(byte soundID, Vector position, int blockId, int entityType, boolean isBabyMob, boolean isGlobal) {
        this.soundID = soundID;
        this.position = position;
        this.blockId = blockId;
        this.entityType = entityType;
        this.isBabyMob = isBabyMob;
        this.isGlobal = isGlobal;
    }

    public byte getSoundID() {
        return soundID;
    }

    public Vector getPosition() {
        return position;
    }

    public int getBlockId() {
        return blockId;
    }

    public int getEntityType() {
        return entityType;
    }

    public boolean getIsBabyMob() {
        return isBabyMob;
    }

    public boolean getIsGlobal() {
        return isGlobal;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.LEVEL_SOUND_EVENT;
    }
}

