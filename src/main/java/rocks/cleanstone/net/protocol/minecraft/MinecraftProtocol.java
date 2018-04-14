package rocks.cleanstone.net.protocol.minecraft;

import rocks.cleanstone.net.packet.PacketFactory;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;
import rocks.cleanstone.net.protocol.Protocol;

public interface MinecraftProtocol extends Protocol {
    <T extends MinecraftPacket> PacketFactory<T> getFactory(Class<T> packet);
}
