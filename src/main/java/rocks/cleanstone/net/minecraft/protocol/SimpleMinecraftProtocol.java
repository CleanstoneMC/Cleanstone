package rocks.cleanstone.net.minecraft.protocol;

import com.google.common.base.Preconditions;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.LayeredProtocol;
import rocks.cleanstone.net.protocol.ProtocolState;

public class SimpleMinecraftProtocol extends LayeredProtocol {

    private PacketTypeRegistry registry;

    @Autowired
    private Collection<MinecraftServerProtocolLayer> serverProtocolLayers;

    public SimpleMinecraftProtocol() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(MinecraftOutboundPacketType.values());
        registry.registerPacketType(MinecraftInboundPacketType.values());
    }

    public void init() {
        serverProtocolLayers.forEach(this::registerProtocolLayer);
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return registry;
    }

    @Override
    public int translateInboundPacketID(int clientPacketID, Connection connection) {
        MinecraftServerProtocolLayer layer = (MinecraftServerProtocolLayer) getServerLayerFromClientLayer
                (connection.getClientProtocolLayer());
        Preconditions.checkNotNull(layer, "Cannot find ServerLayer by ClientLayer "
                + connection.getClientProtocolLayer().getName());

        PacketType packetType = layer.getPacketType(clientPacketID, connection.getProtocolState());
        Preconditions.checkNotNull(packetType, "Missing codec: Cannot find packetType by clientPacketID " +
                String.format("0x%02X", clientPacketID) + " and protocolState " + connection
                .getProtocolState() + " in " + layer.getClass().getSimpleName());
        return packetType.getTypeID();
    }

    @Override
    public int translateOutboundPacketID(int serverPacketID, Connection connection) {
        MinecraftServerProtocolLayer layer = (MinecraftServerProtocolLayer) getServerLayerFromClientLayer
                (connection.getClientProtocolLayer());
        Preconditions.checkNotNull(layer, "Cannot find ServerLayer by ClientLayer "
                + connection.getClientProtocolLayer().getName());

        return layer.getProtocolPacketID(MinecraftOutboundPacketType.byTypeID(serverPacketID));
    }

    @Override
    public ClientProtocolLayer getDefaultClientLayer() {
        return MinecraftClientProtocolLayer.getLatest();
    }

    @Override
    public ProtocolState getDefaultState() {
        return VanillaProtocolState.HANDSHAKE;
    }
}
