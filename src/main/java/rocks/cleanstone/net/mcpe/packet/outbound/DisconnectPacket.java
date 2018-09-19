package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class DisconnectPacket implements Packet {

    private final boolean hideDisconnectReason;
    private final String message;

    public DisconnectPacket(boolean hideDisconnectReason, String message) {
        this.hideDisconnectReason =  hideDisconnectReason;
        this.message =  message;
    }

    public boolean getHideDisconnectReason() {
        return hideDisconnectReason;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.DISCONNECT;
    }
}

