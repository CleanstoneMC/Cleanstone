package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.UUID;

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
