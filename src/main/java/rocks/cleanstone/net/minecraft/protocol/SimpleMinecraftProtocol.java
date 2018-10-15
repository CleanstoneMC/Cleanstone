package rocks.cleanstone.net.minecraft.protocol;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.LayeredProtocol;
import rocks.cleanstone.net.protocol.ProtocolState;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
public class SimpleMinecraftProtocol extends LayeredProtocol {

    private final PacketTypeRegistry registry;

    @Autowired
    private Collection<MinecraftServerProtocolLayer> serverProtocolLayers;

    public SimpleMinecraftProtocol() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(MinecraftOutboundPacketType.values());
        registry.registerPacketType(MinecraftInboundPacketType.values());
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
    public PacketType translateInboundPacketID(int clientPacketID, Connection connection) {
        final MinecraftServerProtocolLayer layer = (MinecraftServerProtocolLayer) getServerLayerFromClientLayer
                (connection.getClientProtocolLayer());
        Preconditions.checkNotNull(layer, "Cannot find ServerLayer by ClientLayer "
                + connection.getClientProtocolLayer().getName());

        final PacketType packetType = layer.getPacketType(clientPacketID, connection.getProtocolState());
        Preconditions.checkNotNull(packetType, "Missing codec: Cannot find packetType by clientPacketID " +
                String.format("0x%02X", clientPacketID) + " and protocolState " + connection
                .getProtocolState() + " in " + layer.getClass().getSimpleName());
        return packetType;
    }

    @Override
    public int translateOutboundPacketID(PacketType packetType, Connection connection) {
        final MinecraftServerProtocolLayer layer = (MinecraftServerProtocolLayer) getServerLayerFromClientLayer
                (connection.getClientProtocolLayer());
        Preconditions.checkNotNull(layer, "Cannot find ServerLayer by ClientLayer "
                + connection.getClientProtocolLayer().getName());

        return layer.getProtocolPacketID(packetType);
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
