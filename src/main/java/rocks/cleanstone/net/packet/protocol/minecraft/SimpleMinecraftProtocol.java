package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.protocol.LayeredProtocol;

public class SimpleMinecraftProtocol extends LayeredProtocol {

    private PacketTypeRegistry registry;

    public void init() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(MinecraftSendPacketType.values());
        registry.registerPacketType(MinecraftReceivePacketType.values());
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return registry;
    }
}
