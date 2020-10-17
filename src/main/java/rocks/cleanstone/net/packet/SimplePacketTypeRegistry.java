package rocks.cleanstone.net.packet;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class SimplePacketTypeRegistry extends PacketTypeRegistry {

    private final Set<PacketType> packetTypes = Sets.newConcurrentHashSet();

    @Override
    public void registerPacketType(PacketType... packetTypes) {
        this.packetTypes.addAll(Arrays.asList(packetTypes));
    }

    @Override
    public void unregisterPacketType(PacketType... packetTypes) {
        this.packetTypes.removeAll(Arrays.asList(packetTypes));
    }

    @Override
    public Collection<PacketType> getPacketTypes() {
        return packetTypes;
    }
}
