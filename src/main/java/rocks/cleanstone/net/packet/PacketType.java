package rocks.cleanstone.net.packet;

import rocks.cleanstone.net.packet.protocol.ProtocolType;

public interface PacketType {
    int getTypeId();

    ProtocolType getProtocolType();
}
