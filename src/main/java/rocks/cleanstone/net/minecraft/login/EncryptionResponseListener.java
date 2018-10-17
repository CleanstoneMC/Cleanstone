package rocks.cleanstone.net.minecraft.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;

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
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof EncryptionResponsePacket) {
            EncryptionResponsePacket packet = (EncryptionResponsePacket) event.getPacket();
            try {
                loginManager.onEncryptionResponse(event.getConnection(), packet);
            } catch (Exception e) {
                log.error("Error occurred while handling encryption response", e);
                loginManager.stopLogin(event.getConnection(), Text.of("Invalid encryption response"));
            }
        }
    }
}