package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ShowProfilePacket implements Packet {

    private final String xUID;

    public ShowProfilePacket(String xUID) {
        this.xUID = xUID;
    }

    public String getXUID() {
        return xUID;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SHOW_PROFILE;
    }
}

