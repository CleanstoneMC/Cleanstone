package rocks.cleanstone.net.minecraft.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.EncryptionResponsePacket;

public class EncryptionResponseListener {

    private final LoginManager loginManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
                logger.error("Error occurred while handling encryption response", e);
                loginManager.stopLogin(event.getConnection(), Text.of("Invalid encryption response"));
            }
        }
    }
}