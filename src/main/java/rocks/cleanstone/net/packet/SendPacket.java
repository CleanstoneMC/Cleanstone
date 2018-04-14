package rocks.cleanstone.net.packet;

public abstract class SendPacket implements Packet {

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.SEND;
    }
}
