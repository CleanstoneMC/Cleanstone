package rocks.cleanstone.net.packet;

import rocks.cleanstone.net.packet.protocol.ProtocolType;

public interface PacketType {
    int getTypeID();

    ProtocolType getProtocolType();

    Class<? extends Packet> getPacketClass();

    PacketDirection getDirection();
}
