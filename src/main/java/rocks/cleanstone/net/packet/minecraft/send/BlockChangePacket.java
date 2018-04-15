package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class BlockChangePacket extends SendPacket {

    private final Position location;
    private final int blockID;

    public BlockChangePacket(Position location, int blockID) {
        this.location = location;
        this.blockID = blockID;
    }

    public Position getLocation() {
        return location;
    }

    public int getBlockID() {
        return blockID;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.BLOCK_CHANGE;
    }
}
