package rocks.cleanstone.net.packet;

import java.util.Collection;

public abstract class PacketTypeRegistry {
    public abstract void registerPacketType(PacketType... packetTypes);

    public abstract void unregisterPacketType(PacketType... packetTypes);

    public abstract Collection<PacketType> getPacketTypes();

    public abstract PacketType getPacketType(int packetTypeId);
}
