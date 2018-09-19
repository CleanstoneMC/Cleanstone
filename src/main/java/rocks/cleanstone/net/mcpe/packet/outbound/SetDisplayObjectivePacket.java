package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetDisplayObjectivePacket implements Packet {

    private final String displaySlot;
    private final String objectiveName;
    private final String displayName;
    private final String criteriaName;
    private final int sortOrder;

    public SetDisplayObjectivePacket(String displaySlot, String objectiveName, String displayName, String criteriaName, int sortOrder) {
        this.displaySlot =  displaySlot;
        this.objectiveName =  objectiveName;
        this.displayName =  displayName;
        this.criteriaName =  criteriaName;
        this.sortOrder =  sortOrder;
    }

    public String getDisplaySlot() {
        return displaySlot;
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SET_DISPLAY_OBJECTIVE;
    }
}

