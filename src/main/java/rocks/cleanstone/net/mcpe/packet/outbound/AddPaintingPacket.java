package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AddPaintingPacket implements Packet {

    private final long entityIDSelf;
    private final long runtimeEntityID;
    private final BlockCoordinates coordinates;
    private final int direction;
    private final String title;

    public AddPaintingPacket(long entityIDSelf, long runtimeEntityID, BlockCoordinates coordinates, int direction, String title) {
        this.entityIDSelf =  entityIDSelf;
        this.runtimeEntityID =  runtimeEntityID;
        this.coordinates =  coordinates;
        this.direction =  direction;
        this.title =  title;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public int getDirection() {
        return direction;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.ADD_PAINTING;
    }
}

