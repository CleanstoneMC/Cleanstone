package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.ClientStatus;

public class ClientStatusPacket {

    private final ClientStatus clientStatus;

    public ClientStatusPacket(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public ClientStatusPacket(int clientStatusID) {
        this.clientStatus = ClientStatus.fromStatusID(clientStatusID);
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CLIENT_STATUS;
    }
}

