package rocks.cleanstone.net.packet;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Nullable;

public class SimplePacketTypeRegistry implements PacketTypeRegistry {

    private Set<PacketType> packetTypes = Sets.newConcurrentHashSet();

    public void init() {
    }

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

    @Override
    @Nullable
    public PacketType getPacketType(int packetTypeId) {
        return packetTypes.stream().filter(
                (packetType) -> packetType.getTypeId() == packetTypeId
        ).findFirst().orElse(null);
    }
}
