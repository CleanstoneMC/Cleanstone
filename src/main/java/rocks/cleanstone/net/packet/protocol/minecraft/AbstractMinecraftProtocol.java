package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.PacketCodec;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.protocol.Protocol;

public class AbstractMinecraftProtocol implements Protocol {
    @Override
    public <T extends Packet> PacketCodec<T> getPacketCodec(Class<T> packet) {
        return null;
    }

    @Override
    public PacketTypeRegistry getPacketTypeRegistry() {
        return null;
    }
}
