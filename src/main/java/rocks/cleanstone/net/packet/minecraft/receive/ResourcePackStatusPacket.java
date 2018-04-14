package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.enums.ResourcePackStatus;

public class ResourcePackStatusPacket extends ReceivePacket {

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
        return MinecraftReceivePacketType.RESOURCE_PACK_STATUS;
    }
}
