package rocks.cleanstone.net.packet;

public interface PacketType {

    Class<? extends Packet> getPacketClass();

    PacketDirection getDirection();
}
