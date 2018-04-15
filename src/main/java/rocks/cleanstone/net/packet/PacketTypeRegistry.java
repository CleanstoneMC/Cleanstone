package rocks.cleanstone.net.packet;

import java.util.Collection;

public interface PacketTypeRegistry {
    void registerPacketType(PacketType... packetTypes);

    void unregisterPacketType(PacketType... packetTypes);

    Collection<PacketType> getPacketTypes();

    PacketType getPacketType(int packetTypeId);
}
