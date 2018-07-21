package rocks.cleanstone.net.minecraft.protocol;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.MinecraftProtocolLayer_v1_12_2;
import rocks.cleanstone.net.minecraft.protocol.v1_13.MinecraftProtocolLayer_v1_13;
import rocks.cleanstone.net.packet.*;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.LayeredProtocol;
import rocks.cleanstone.net.protocol.ProtocolState;

public class SimpleMinecraftProtocol extends LayeredProtocol {

    private PacketTypeRegistry registry;

    @Autowired
    public SimpleMinecraftProtocol(MaterialRegistry materialRegistry) {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(MinecraftOutboundPacketType.values());
        registry.registerPacketType(MinecraftInboundPacketType.values());
        registerProtocolLayer(new MinecraftProtocolLayer_v1_12_2(materialRegistry));
        registerProtocolLayer(new MinecraftProtocolLayer_v1_13(materialRegistry));
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return registry;
    }

    @Override
    public int translateInboundPacketID(int clientPacketID, Connection connection) {
        MinecraftServerProtocolLayer layer = ((MinecraftServerProtocolLayer) getServerLayerFromClientLayer
                (connection.getClientProtocolLayer()));
        Preconditions.checkNotNull(layer, "Cannot find ServerLayer by ClientLayer "
                + connection.getClientProtocolLayer().toString());
        PacketType packetType = layer.getPacketType(clientPacketID, connection.getProtocolState());
        Preconditions.checkNotNull(packetType, "Missing codec: Cannot find packetType by clientPacketID " +
                String.format("0x%02X", clientPacketID) + " and protocolState " + connection
                .getProtocolState());
        return packetType.getTypeID();
    }

    @Override
    public int translateOutboundPacketID(int serverPacketID, Connection connection) {
        return ((MinecraftServerProtocolLayer) getServerLayerFromClientLayer(connection.getClientProtocolLayer()))
                .getProtocolPacketID(MinecraftOutboundPacketType.byTypeID(serverPacketID));
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
