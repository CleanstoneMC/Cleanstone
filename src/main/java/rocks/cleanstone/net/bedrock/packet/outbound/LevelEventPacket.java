package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class LevelEventPacket implements Packet {

    private final int eventID;
    private final Vector position;
    private final int data;

    public LevelEventPacket(int eventID, Vector position, int data) {
        this.eventID = eventID;
        this.position = position;
        this.data = data;
    }

    public int getEventID() {
        return eventID;
    }

    public Vector getPosition() {
        return position;
    }

    public int getData() {
        return data;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.LEVEL_EVENT;
    }
}

