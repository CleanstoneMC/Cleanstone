package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class OpenWindowPacket extends SendPacket {

    private final byte windowID;
    private final String windowType;
    private final String windowTitle;
    private final byte numberOfSlots;
    private final int entityID;

    public OpenWindowPacket(byte windowID, String windowType, String windowTitle, byte numberOfSlots, int entityID) {
        this.windowID = windowID;
        this.windowType = windowType;
        this.windowTitle = windowTitle;
        this.numberOfSlots = numberOfSlots;
        this.entityID = entityID;
    }

    public byte getWindowID() {
        return windowID;
    }

    public String getWindowType() {
        return windowType;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public byte getNumberOfSlots() {
        return numberOfSlots;
    }

    public int getEntityID() {
        return entityID;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.OPEN_WINDOW;
    }
}
