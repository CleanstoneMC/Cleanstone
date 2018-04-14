package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.StandardPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.ClientStatus;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;

public class ClientStatusPacket implements MinecraftPacket {

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
        return StandardPacketType.MINECRAFT;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.RECEIVE;
    }
}

