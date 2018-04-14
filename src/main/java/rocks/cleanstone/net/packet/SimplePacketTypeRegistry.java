package rocks.cleanstone.net.packet;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import rocks.cleanstone.net.packet.cleanstone.CleanstoneReceivePacketType;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneSendPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class SimplePacketTypeRegistry implements PacketTypeRegistry {

    private Set<PacketType> packetTypes = Sets.newConcurrentHashSet();

    public void init() {
        registerPacketType(CleanstoneReceivePacketType.values());
        registerPacketType(CleanstoneSendPacketType.values());
        registerPacketType(MinecraftReceivePacketType.values());
        registerPacketType(MinecraftSendPacketType.values());
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
}
