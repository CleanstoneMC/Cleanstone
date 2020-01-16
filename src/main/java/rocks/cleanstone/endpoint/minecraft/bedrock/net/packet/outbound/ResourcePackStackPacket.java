package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.ResourcePackIdVersions;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResourcePackStackPacket implements Packet {

    private final boolean mustAccept;
    private final ResourcePackIdVersions behaviorPackIdVersions;
    private final ResourcePackIdVersions resourcePackIdVersions;

    public ResourcePackStackPacket(boolean mustAccept, ResourcePackIdVersions behaviorPackIdVersions, ResourcePackIdVersions resourcePackIdVersions) {
        this.mustAccept = mustAccept;
        this.behaviorPackIdVersions = behaviorPackIdVersions;
        this.resourcePackIdVersions = resourcePackIdVersions;
    }

    public boolean getMustAccept() {
        return mustAccept;
    }

    public ResourcePackIdVersions getBehaviorPackIdVersions() {
        return behaviorPackIdVersions;
    }

    public ResourcePackIdVersions getResourcePackIdVersions() {
        return resourcePackIdVersions;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.RESOURCE_PACK_STACK;
    }
}

