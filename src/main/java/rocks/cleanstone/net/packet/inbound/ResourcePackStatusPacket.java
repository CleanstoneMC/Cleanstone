package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.enums.ResourcePackStatus;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResourcePackStatusPacket implements Packet {

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
