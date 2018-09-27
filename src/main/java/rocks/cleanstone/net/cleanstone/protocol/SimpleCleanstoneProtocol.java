package rocks.cleanstone.net.cleanstone.protocol;

import org.springframework.stereotype.Component;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.cleanstone.packet.CleanstoneInboundPacketType;
import rocks.cleanstone.net.cleanstone.packet.CleanstoneOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.protocol.ProtocolState;

import javax.annotation.PostConstruct;

@Component
public class SimpleCleanstoneProtocol implements Protocol {

    private PacketTypeRegistry registry;

    @PostConstruct
    public void init() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(CleanstoneOutboundPacketType.values());
        registry.registerPacketType(CleanstoneInboundPacketType.values());
    }

    @Override
    public <T extends Packet> OutboundPacketCodec<T> getOutboundPacketCodec(Class<T> packet, ClientProtocolLayer clientLayer) {
        return null;
    }

    @Override
    public <T extends Packet> InboundPacketCodec<T> getInboundPacketCodec(Class<T> packet, ClientProtocolLayer clientLayer) {
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
        return CleanstoneClientProtocolLayer.LATEST;
    }

    @Override
    public ProtocolState getDefaultState() {
        return null;
    }
}
