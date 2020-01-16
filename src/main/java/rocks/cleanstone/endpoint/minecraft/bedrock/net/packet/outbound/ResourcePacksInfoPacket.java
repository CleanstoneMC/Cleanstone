package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.ResourcePackInfos;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResourcePacksInfoPacket implements Packet {

    private final boolean mustAccept;
    private final ResourcePackInfos behahaviorPackInfos;
    private final ResourcePackInfos resourcePackInfos;

    public ResourcePacksInfoPacket(boolean mustAccept, ResourcePackInfos behahaviorPackInfos, ResourcePackInfos resourcePackInfos) {
        this.mustAccept = mustAccept;
        this.behahaviorPackInfos = behahaviorPackInfos;
        this.resourcePackInfos = resourcePackInfos;
    }

    public boolean getMustAccept() {
        return mustAccept;
    }

    public ResourcePackInfos getBehahaviorPackInfos() {
        return behahaviorPackInfos;
    }

    public ResourcePackInfos getResourcePackInfos() {
        return resourcePackInfos;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.RESOURCE_PACKS_INFO;
    }
}

