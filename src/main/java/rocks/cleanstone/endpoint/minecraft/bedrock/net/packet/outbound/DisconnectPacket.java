package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class DisconnectPacket implements Packet {

    private final boolean hideDisconnectReason;
    private final String message;

    public DisconnectPacket(boolean hideDisconnectReason, String message) {
        this.hideDisconnectReason = hideDisconnectReason;
        this.message = message;
    }

    public boolean getHideDisconnectReason() {
        return hideDisconnectReason;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.DISCONNECT;
    }
}

