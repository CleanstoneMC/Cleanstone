package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketFactory;
import rocks.cleanstone.net.packet.protocol.Protocol;

public class AbstractMinecraftProtocol implements Protocol {
    @Override
    public <T extends Packet> PacketFactory<T> getFactory(Class<T> packet) {
        return null;
    }
}
