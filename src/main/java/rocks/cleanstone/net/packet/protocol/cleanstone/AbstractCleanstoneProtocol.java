package rocks.cleanstone.net.packet.protocol.cleanstone;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.SimplePacketTypeRegistry;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneReceivePacketType;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneSendPacketType;
import rocks.cleanstone.net.packet.protocol.PacketCodec;
import rocks.cleanstone.net.packet.protocol.Protocol;

public abstract class AbstractCleanstoneProtocol implements Protocol {

    private PacketTypeRegistry registry;

    public void init() {
        registry = new SimplePacketTypeRegistry();
        registry.registerPacketType(CleanstoneSendPacketType.values());
        registry.registerPacketType(CleanstoneReceivePacketType.values());
    }

    @Override
    public <T extends Packet> PacketCodec<T> getPacketCodec(Class<T> packet) {
        return null;
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return registry;
    }
}
