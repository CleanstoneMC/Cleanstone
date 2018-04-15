package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class BlockBreakAnimationPacket extends SendPacket {

    private final int entityID;
    private final Position location;
    private final byte destroyStage;

    public BlockBreakAnimationPacket(int entityID, Position location, byte destroyStage) {
        this.entityID = entityID;
        this.location = location;
        this.destroyStage = destroyStage;
    }

    public int getEntityID() {
        return entityID;
    }

    public Position getLocation() {
        return location;
    }

    public byte getDestroyStage() {
        return destroyStage;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.BLOCK_BREAK_ANIMATION;
    }
}
