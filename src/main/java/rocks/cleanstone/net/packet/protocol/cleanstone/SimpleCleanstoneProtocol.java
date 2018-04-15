package rocks.cleanstone.net.packet.protocol.cleanstone;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneReceivePacketType;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneSendPacketType;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.PacketCodec;
import rocks.cleanstone.net.packet.protocol.Protocol;

public class SimpleCleanstoneProtocol implements Protocol {

    private PacketTypeRegistry registry;

    public void init() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(CleanstoneSendPacketType.values());
        registry.registerPacketType(CleanstoneReceivePacketType.values());
    }

    @Override
    public <T extends Packet> PacketCodec<T> getPacketCodec(Class<T> packet,
                                                            ClientProtocolLayer clientLayer) {
        return null;
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return registry;
    }

    @Override
    public int translateIncomingPacketId(int clientPacketId, ClientProtocolLayer clientLayer) {
        return clientPacketId;
    }

    @Override
    public int translateOutgoingPacketId(int serverPacketId, ClientProtocolLayer clientLayer) {
        return serverPacketId;
    }
}
