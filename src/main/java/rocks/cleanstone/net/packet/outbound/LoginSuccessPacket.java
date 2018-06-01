package rocks.cleanstone.net.packet.outbound;

import java.util.UUID;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class LoginSuccessPacket implements Packet {

    private final UUID uuid;
    private final String userName;

    public LoginSuccessPacket(UUID uuid, String userName) {
        this.uuid = uuid;
        this.userName = userName;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.LOGIN_SUCCESS;
    }
}
