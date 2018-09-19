package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TakeItemEntityPacket implements Packet {

    private final long runtimeEntityID;
    private final long target;

    public TakeItemEntityPacket(long runtimeEntityID, long target) {
        this.runtimeEntityID =  runtimeEntityID;
        this.target =  target;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public long getTarget() {
        return target;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.TAKE_ITEM_ENTITY;
    }
}

