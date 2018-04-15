package rocks.cleanstone.net.packet;

public abstract class InboundPacket implements Packet {

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.INBOUND;
    }
}
