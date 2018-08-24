package rocks.cleanstone.net.mcpe.protocol;

import org.springframework.stereotype.Component;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.mcpe.MCPEClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.*;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.protocol.ProtocolState;

import javax.annotation.PostConstruct;

@Component
public class SimpleMCPEProtocol implements Protocol {

    private PacketTypeRegistry registry;

    @PostConstruct
    public void init() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(MinecraftOutboundPacketType.values());
        registry.registerPacketType(MinecraftInboundPacketType.values());
    }

    @Override
    public PacketCodec getPacketCodec(Class<? extends Packet> packet, ClientProtocolLayer layer) {
        return null;
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return registry;
    }

    @Override
    public int translateInboundPacketID(int clientPacketID, Connection connection) {
        return clientPacketID;
    }

    @Override
    public int translateOutboundPacketID(int serverPacketID, Connection connection) {
        return serverPacketID;
    }

    @Override
    public ClientProtocolLayer getDefaultClientLayer() {
        return MCPEClientProtocolLayer.getLatest();
    }

    @Override
    public ProtocolState getDefaultState() {
        return VanillaProtocolState.HANDSHAKE;
    }
}
