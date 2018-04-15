package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.InboundPacket;

public class PluginMessagePacket extends InboundPacket {

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
        return null;
    }
}
