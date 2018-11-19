package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.Records;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class ExplodePacket implements Packet {

    private final Vector position;
    private final int radius;
    private final Records records;

    public ExplodePacket(Vector position, int radius, Records records) {
        this.position = position;
        this.radius = radius;
        this.records = records;
    }

    public Vector getPosition() {
        return position;
    }

    public int getRadius() {
        return radius;
    }

    public Records getRecords() {
        return records;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.EXPLODE;
    }
}

