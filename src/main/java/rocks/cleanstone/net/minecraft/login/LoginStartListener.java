package rocks.cleanstone.net.minecraft.login;

import org.springframework.context.event.EventListener;

import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.LoginStartPacket;

public class LoginStartListener {

    private final LoginManager loginManager;

    public LoginStartListener(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof LoginStartPacket) {
            LoginStartPacket packet = (LoginStartPacket) event.getPacket();

            String playerName = packet.getPlayerName();
            loginManager.startLogin(event.getConnection(), playerName);
        }
    }
}