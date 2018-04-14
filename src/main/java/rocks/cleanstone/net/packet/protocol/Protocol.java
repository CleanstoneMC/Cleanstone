package rocks.cleanstone.net.packet.protocol;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketFactory;

public interface Protocol {
    <T extends Packet> PacketFactory<T> getFactory(Class<T> packet);

}
