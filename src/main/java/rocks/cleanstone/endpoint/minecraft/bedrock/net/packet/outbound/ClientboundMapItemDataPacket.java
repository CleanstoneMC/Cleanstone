package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.MapInfo;
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
        return BedrockOutboundPacketType.CLIENTBOUND_MAP_ITEM_DATA_;
    }
}

