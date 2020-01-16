package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class RemoveObjectivePacket implements Packet {

    private final String objectiveName;

    public RemoveObjectivePacket(String objectiveName) {
        this.objectiveName = objectiveName;
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.REMOVE_OBJECTIVE;
    }
}

