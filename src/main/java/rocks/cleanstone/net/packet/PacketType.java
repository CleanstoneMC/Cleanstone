package rocks.cleanstone.net.packet;

public interface PacketType {
    int getTypeId();

    Class<? extends Packet> getPacketClass();
}
