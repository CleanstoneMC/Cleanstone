package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ContainerClosePacket implements Packet {

    private final byte windowID;

    public ContainerClosePacket(byte windowID) {
        this.windowID =  windowID;
    }

    public byte getWindowID() {
        return windowID;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.CONTAINER_CLOSE;
    }
}

