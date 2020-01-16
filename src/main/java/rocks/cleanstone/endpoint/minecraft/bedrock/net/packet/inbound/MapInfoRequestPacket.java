package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MapInfoRequestPacket implements Packet {

    private final long mapID;

    public MapInfoRequestPacket(long mapID) {
        this.mapID = mapID;
    }

    public long getMapID() {
        return mapID;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.MAP_INFO_REQUEST;
    }
}

