package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetEntityLinkPacket implements Packet {

    private final long riddenID;
    private final long riderID;
    private final byte linkType;
    private final byte unknown;

    public SetEntityLinkPacket(long riddenID, long riderID, byte linkType, byte unknown) {
        this.riddenID =  riddenID;
        this.riderID =  riderID;
        this.linkType =  linkType;
        this.unknown =  unknown;
    }

    public long getRiddenID() {
        return riddenID;
    }

    public long getRiderID() {
        return riderID;
    }

    public byte getLinkType() {
        return linkType;
    }

    public byte getUnknown() {
        return unknown;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SET_ENTITY_LINK;
    }
}

