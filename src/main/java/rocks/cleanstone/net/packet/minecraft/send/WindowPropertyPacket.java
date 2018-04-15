package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class WindowPropertyPacket extends SendPacket {

    private final byte windowID;
    private final short property;
    private final short value;

    public WindowPropertyPacket(byte windowID, short property, short value) {
        this.windowID = windowID;
        this.property = property;
        this.value = value;
    }

    public byte getWindowID() {
        return windowID;
    }

    public short getProperty() {
        return property;
    }

    public short getValue() {
        return value;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.WINDOW_PROPERTY;
    }
}
