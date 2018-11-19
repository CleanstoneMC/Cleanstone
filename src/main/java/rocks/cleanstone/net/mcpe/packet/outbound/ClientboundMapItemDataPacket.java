package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.MapInfo;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ClientboundMapItemDataPacket implements Packet {

    private final MapInfo mapInfo;

    public ClientboundMapItemDataPacket(MapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }

    public MapInfo getMapInfo() {
        return mapInfo;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.CLIENTBOUND_MAP_ITEM_DATA_;
    }
}

