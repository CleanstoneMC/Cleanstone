package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.ResourcePackIds;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResourcePackClientResponsePacket implements Packet {

    private final byte responseStatus;
    private final ResourcePackIds resourcePackIds;

    public ResourcePackClientResponsePacket(byte responseStatus, ResourcePackIds resourcePackIds) {
        this.responseStatus = responseStatus;
        this.resourcePackIds = resourcePackIds;
    }

    public byte getResponseStatus() {
        return responseStatus;
    }

    public ResourcePackIds getResourcePackIds() {
        return resourcePackIds;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.RESOURCE_PACK_CLIENT_RESPONSE;
    }
}

