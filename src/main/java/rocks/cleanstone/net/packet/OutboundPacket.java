package rocks.cleanstone.net.packet;

public abstract class OutboundPacket implements Packet {

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.OUTBOUND;
    }
}
