package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class PluginMessagePacket extends SendPacket {

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
        return MinecraftSendPacketType.PLUGIN_MESSAGE;
    }
}
