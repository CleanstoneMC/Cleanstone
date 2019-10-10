package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerHotbarPacket implements Packet {

    private final int selectedSlot;
    private final byte windowID;
    private final boolean selectSlot;

    public PlayerHotbarPacket(int selectedSlot, byte windowID, boolean selectSlot) {
        this.selectedSlot = selectedSlot;
        this.windowID = windowID;
        this.selectSlot = selectSlot;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    public byte getWindowID() {
        return windowID;
    }

    public boolean getSelectSlot() {
        return selectSlot;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.PLAYER_HOTBAR;
    }
}

