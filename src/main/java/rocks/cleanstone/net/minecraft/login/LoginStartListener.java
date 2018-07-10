package rocks.cleanstone.net.minecraft.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.LoginStartPacket;

public class LoginStartListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LoginManager loginManager;

    @Autowired
    public LoginStartListener(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Async(value = "mcLoginExec")
    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof LoginStartPacket) {
            LoginStartPacket packet = (LoginStartPacket) event.getPacket();
            String playerName = packet.getPlayerName();
            loginManager.startLogin(event.getConnection(), playerName);
        }
    }
}