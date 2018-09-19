package rocks.cleanstone.net.mcpe.protocol;

import org.springframework.stereotype.Component;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.mcpe.MCPEClientProtocolLayer;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
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
    public PacketType translateInboundPacketID(int clientPacketID, Connection connection) {
        return null; // TODO
    }

    @Override
    public int translateOutboundPacketID(PacketType packetType, Connection connection) {
        return -1; // TODO
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
