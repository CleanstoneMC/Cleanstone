package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
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
        return MCPEOutboundPacketType.ADD_BEHAVIOR_TREE;
    }
}

