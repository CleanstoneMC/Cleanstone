package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.enums.ClientStatus;

public class ClientStatusPacket extends ReceivePacket {

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
        return MinecraftReceivePacketType.CLIENT_STATUS;
    }
}

