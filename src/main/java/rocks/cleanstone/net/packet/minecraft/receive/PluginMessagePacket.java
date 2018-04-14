package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;

public class PluginMessagePacket extends ReceivePacket {

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
