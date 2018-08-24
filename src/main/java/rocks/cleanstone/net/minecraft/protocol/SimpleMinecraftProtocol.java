package rocks.cleanstone.net.minecraft.protocol;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.*;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.LayeredProtocol;
import rocks.cleanstone.net.protocol.ProtocolState;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
public class SimpleMinecraftProtocol extends LayeredProtocol {

    private PacketTypeRegistry registry;

    private final Collection<MinecraftServerProtocolLayer> serverProtocolLayers;

    @Autowired
    public SimpleMinecraftProtocol(Collection<MinecraftServerProtocolLayer> serverProtocolLayers) {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(MinecraftOutboundPacketType.values());
        registry.registerPacketType(MinecraftInboundPacketType.values());

        this.serverProtocolLayers = serverProtocolLayers;
    }

    @PostConstruct
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
