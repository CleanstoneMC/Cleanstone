package rocks.cleanstone.endpoint.minecraft.vanilla.net.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.event.InboundPacketEvent;

@Slf4j
@Component
public class EncryptionResponseListener {
    private final LoginManager loginManager;

    @Autowired
    public EncryptionResponseListener(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Async(value = "mcLoginExec")
    @EventListener
    public void onReceive(InboundPacketEvent<EncryptionResponsePacket> event) {
        EncryptionResponsePacket packet = event.getPacket();
        try {
            loginManager.handleEncryptionResponse(event.getConnection(), packet);
        } catch (Exception e) {
            log.error("Error occurred while handling encryption response", e);
            loginManager.stopLogin(event.getConnection(), Text.of("Invalid encryption response"));
        }
    }
}