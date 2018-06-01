package rocks.cleanstone.net.cleanstone.protocol;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.cleanstone.packet.CleanstoneInboundPacketType;
import rocks.cleanstone.net.cleanstone.packet.CleanstoneOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.protocol.ProtocolState;

public class SimpleCleanstoneProtocol implements Protocol {

    private PacketTypeRegistry registry;

    public void init() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(CleanstoneOutboundPacketType.values());
        registry.registerPacketType(CleanstoneInboundPacketType.values());
    }

    @Override
    public PacketCodec getPacketCodec(Class<? extends Packet> packet, ClientProtocolLayer clientLayer) {
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
        return CleanstoneClientProtocolLayer.LATEST;
    }

    @Override
    public ProtocolState getDefaultState() {
        return null;
    }
}
