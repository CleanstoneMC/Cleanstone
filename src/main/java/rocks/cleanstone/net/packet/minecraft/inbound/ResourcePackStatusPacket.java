package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.ResourcePackStatus;

public class ResourcePackStatusPacket extends InboundPacket {

    private final ResourcePackStatus result;

    public ResourcePackStatusPacket(int result) {
        this.result = ResourcePackStatus.fromStatusID(result);
    }

    public ResourcePackStatusPacket(ResourcePackStatus result) {
        this.result = result;
    }

    public ResourcePackStatus getResult() {
        return result;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.RESOURCE_PACK_STATUS;
    }
}
