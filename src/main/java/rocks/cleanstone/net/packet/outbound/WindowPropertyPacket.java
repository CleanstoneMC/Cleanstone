package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class WindowPropertyPacket implements Packet {

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
        return MinecraftOutboundPacketType.WINDOW_PROPERTY;
    }
}
