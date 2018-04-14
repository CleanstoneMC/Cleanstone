package rocks.cleanstone.net.packet;

public abstract class ReceivePacket implements Packet {

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.RECEIVE;
    }
}
