package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.ScorePacketInfos;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetScorePacket implements Packet {

    private final byte packetType;
    private final ScorePacketInfos scorePacketInfos;

    public SetScorePacket(byte packetType, ScorePacketInfos scorePacketInfos) {
        this.packetType = packetType;
        this.scorePacketInfos = scorePacketInfos;
    }

    public byte getPacketType() {
        return packetType;
    }

    public ScorePacketInfos getScorePacketInfos() {
        return scorePacketInfos;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_SCORE;
    }
}

