package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutPluginMessagePacket implements Packet {

    private final String channel;
    private final byte[] data;

    public OutPluginMessagePacket(String channel, byte[] data) {
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
        return MinecraftOutboundPacketType.PLUGIN_MESSAGE;
    }
}
