package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockActionPacket implements Packet {

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
        return MinecraftOutboundPacketType.BLOCK_ACTION;
    }
}
