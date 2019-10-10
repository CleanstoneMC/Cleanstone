package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetTimePacket implements Packet {

    private final int time;

    public SetTimePacket(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_TIME;
    }
}

