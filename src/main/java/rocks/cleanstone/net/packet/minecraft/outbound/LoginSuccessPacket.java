package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

import java.util.UUID;

public class LoginSuccessPacket {

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
