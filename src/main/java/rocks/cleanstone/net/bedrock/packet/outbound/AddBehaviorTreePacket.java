package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AddBehaviorTreePacket implements Packet {

    private final String behaviorTree;

    public AddBehaviorTreePacket(String behaviorTree) {
        this.behaviorTree = behaviorTree;
    }

    public String getBehaviorTree() {
        return behaviorTree;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.ADD_BEHAVIOR_TREE;
    }
}

