package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockPickRequestPacket implements Packet {

    private final int x;
    private final int y;
    private final int z;
    private final boolean addUserData;
    private final byte selectedSlot;

    public BlockPickRequestPacket(int x, int y, int z, boolean addUserData, byte selectedSlot) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.addUserData = addUserData;
        this.selectedSlot = selectedSlot;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean getAddUserData() {
        return addUserData;
    }

    public byte getSelectedSlot() {
        return selectedSlot;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.BLOCK_PICK_REQUEST;
    }
}

