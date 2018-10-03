package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InPluginMessagePacket implements Packet {

    private final String channel;
    private final byte[] data;

    public InPluginMessagePacket(String channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.PLUGIN_MESSAGE;
    }
}
