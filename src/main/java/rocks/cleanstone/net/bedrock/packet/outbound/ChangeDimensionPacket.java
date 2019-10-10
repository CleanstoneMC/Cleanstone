package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class ChangeDimensionPacket implements Packet {

    private final int dimension;
    private final Vector position;
    private final boolean respawn;

    public ChangeDimensionPacket(int dimension, Vector position, boolean respawn) {
        this.dimension = dimension;
        this.position = position;
        this.respawn = respawn;
    }

    public int getDimension() {
        return dimension;
    }

    public Vector getPosition() {
        return position;
    }

    public boolean getRespawn() {
        return respawn;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.CHANGE_DIMENSION;
    }
}

