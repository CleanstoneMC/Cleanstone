package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
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
        return MCPEOutboundPacketType.REMOVE_OBJECTIVE;
    }
}

