package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.LayeredProtocol;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.MinecraftProtocolLayer_v1_12_2;

public class SimpleMinecraftProtocol extends LayeredProtocol {

    private PacketTypeRegistry registry;

    public void init() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(MinecraftOutboundPacketType.values());
        registry.registerPacketType(MinecraftInboundPacketType.values());
        registerProtocolLayer(new MinecraftProtocolLayer_v1_12_2());
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return registry;
    }

    @Override
    public int translateInboundPacketId(int clientPacketId, ClientProtocolLayer clientLayer) {
        return 0; // TODO get protocol packet id from codec
    }

    @Override
    public int translateOutboundPacketId(int serverPacketId, ClientProtocolLayer clientLayer) {
        return serverPacketId;
    }
}
