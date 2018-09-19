package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class LabTablePacket implements Packet {

    private final byte uselessByte;
    private final int labTableX;
    private final int labTableY;
    private final int labTableZ;
    private final byte reactionType;

    public LabTablePacket(byte uselessByte, int labTableX, int labTableY, int labTableZ, byte reactionType) {
        this.uselessByte =  uselessByte;
        this.labTableX =  labTableX;
        this.labTableY =  labTableY;
        this.labTableZ =  labTableZ;
        this.reactionType =  reactionType;
    }

    public byte getUselessByte() {
        return uselessByte;
    }

    public int getLabTableX() {
        return labTableX;
    }

    public int getLabTableY() {
        return labTableY;
    }

    public int getLabTableZ() {
        return labTableZ;
    }

    public byte getReactionType() {
        return reactionType;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.LAB_TABLE;
    }
}

