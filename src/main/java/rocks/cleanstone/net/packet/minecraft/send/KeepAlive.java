package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

public class KeepAlive implements Packet {
    @Override
    public PacketType getType() {
        return null;
    }

    @Override
    public PacketDirection getDirection() {
        return null;
    }
}
