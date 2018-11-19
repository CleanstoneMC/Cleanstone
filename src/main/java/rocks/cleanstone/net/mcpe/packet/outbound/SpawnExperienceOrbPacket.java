package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class SpawnExperienceOrbPacket implements Packet {

    private final Vector position;
    private final int count;

    public SpawnExperienceOrbPacket(Vector position, int count) {
        this.position = position;
        this.count = count;
    }

    public Vector getPosition() {
        return position;
    }

    public int getCount() {
        return count;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SPAWN_EXPERIENCE_ORB;
    }
}

