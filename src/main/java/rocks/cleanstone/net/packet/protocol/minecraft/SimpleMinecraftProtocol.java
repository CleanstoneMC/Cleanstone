package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
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
    public int translateInboundPacketID(int clientPacketID, Connection connection) {
        return ((MinecraftServerProtocolLayer) getServerLayerFromClientLayer(connection.getClientProtocolLayer()))
                .getPacketType(clientPacketID, connection.getProtocolState()).getTypeID();
    }

    @Override
    public int translateOutboundPacketID(int serverPacketID, Connection connection) {
        return ((MinecraftServerProtocolLayer) getServerLayerFromClientLayer(connection.getClientProtocolLayer()))
                .getProtocolPacketID(MinecraftOutboundPacketType.byTypeID(serverPacketID));
    }
}
