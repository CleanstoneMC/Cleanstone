package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;

public class LoginManager {

    public LoginManager(Networking networking) {
        networking.registerPacketListener(new HandshakeListener(), MinecraftInboundPacketType.HANDSHAKE);
        networking.registerPacketListener(new LoginStartListener(), MinecraftInboundPacketType.LOGIN_START);
    }
}
