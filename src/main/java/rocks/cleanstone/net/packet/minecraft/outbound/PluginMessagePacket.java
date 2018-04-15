package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class PluginMessagePacket extends OutboundPacket {

    private final String channel;
    private final byte[] data;

    public PluginMessagePacket(String channel, byte[] data) {
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
