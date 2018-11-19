package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetTimePacket implements Packet {

    private final int time;

    public SetTimePacket(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SET_TIME;
    }
}

