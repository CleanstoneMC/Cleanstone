package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetTitlePacket implements Packet {

    private final int packetType;
    private final String text;
    private final int fadeInTime;
    private final int stayTime;
    private final int fadeOutTime;

    public SetTitlePacket(int packetType, String text, int fadeInTime, int stayTime, int fadeOutTime) {
        this.packetType = packetType;
        this.text = text;
        this.fadeInTime = fadeInTime;
        this.stayTime = stayTime;
        this.fadeOutTime = fadeOutTime;
    }

    public int getPacketType() {
        return packetType;
    }

    public String getText() {
        return text;
    }

    public int getFadeInTime() {
        return fadeInTime;
    }

    public int getStayTime() {
        return stayTime;
    }

    public int getFadeOutTime() {
        return fadeOutTime;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_TITLE;
    }
}

