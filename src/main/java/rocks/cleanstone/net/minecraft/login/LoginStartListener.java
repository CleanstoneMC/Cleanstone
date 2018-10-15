package rocks.cleanstone.net.minecraft.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.LoginStartPacket;

@Slf4j
@Component
public class LoginStartListener {
    private final LoginManager loginManager;

    @Autowired
    public LoginStartListener(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Async(value = "mcLoginExec")
    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof LoginStartPacket) {
            final LoginStartPacket packet = (LoginStartPacket) event.getPacket();
            final String playerName = packet.getPlayerName();
            loginManager.startLogin(event.getConnection(), playerName);
        }
    }
}