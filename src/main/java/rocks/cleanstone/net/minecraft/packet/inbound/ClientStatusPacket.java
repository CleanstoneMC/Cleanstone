package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.enums.ClientStatus;

public class ClientStatusPacket implements Packet {

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

