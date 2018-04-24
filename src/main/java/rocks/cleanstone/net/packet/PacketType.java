package rocks.cleanstone.net.packet;

public interface PacketType {
    int getTypeID();

    Class<? extends Packet> getPacketClass();

    PacketDirection getDirection();
}
