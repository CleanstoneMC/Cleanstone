package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ShowCreditsPacket implements Packet {

    private final long runtimeEntityID;
    private final int status;

    public ShowCreditsPacket(long runtimeEntityID, int status) {
        this.runtimeEntityID = runtimeEntityID;
        this.status = status;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SHOW_CREDITS;
    }
}

