package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MapInfoRequestPacket implements Packet {

    private final long mapID;

    public MapInfoRequestPacket(long mapID) {
        this.mapID =  mapID;
    }

    public long getMapID() {
        return mapID;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.MAP_INFO_REQUEST;
    }
}

