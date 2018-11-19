package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ShowCreditsPacket implements Packet {

    private final long runtimeEntityID;
    private final int status;

    public ShowCreditsPacket(long runtimeEntityID, int status) {
        this.runtimeEntityID = runtimeEntityID;
        this.status = status;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SHOW_CREDITS;
    }
}

