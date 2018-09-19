package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayStatusPacket implements Packet {

    private final int status;

    public PlayStatusPacket(int status) {
        this.status =  status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.PLAY_STATUS;
    }
}

