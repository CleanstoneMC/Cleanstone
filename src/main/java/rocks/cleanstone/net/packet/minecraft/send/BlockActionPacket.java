package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class BlockActionPacket extends SendPacket {

    private final Position location;
    private final byte blockAction; //TODO: Add Enum?
    private final byte blockActionParam; //TODO: Add Enum?
    private final int blockType;

    public BlockActionPacket(Position location, byte blockAction, byte blockActionParam, int blockType) {
        this.location = location;
        this.blockAction = blockAction;
        this.blockActionParam = blockActionParam;
        this.blockType = blockType;
    }

    public Position getLocation() {
        return location;
    }

    public byte getBlockAction() {
        return blockAction;
    }

    public byte getBlockActionParam() {
        return blockActionParam;
    }

    public int getBlockType() {
        return blockType;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.BLOCK_ACTION;
    }
}
