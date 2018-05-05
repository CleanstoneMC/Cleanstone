package rocks.cleanstone.net.minecraft.login;

import org.springframework.context.event.EventListener;

import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;

public class EncryptionResponseListener {

    private final LoginManager loginManager;

    public EncryptionResponseListener(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof EncryptionResponsePacket) {
            EncryptionResponsePacket packet = (EncryptionResponsePacket) event.getPacket();
            try {
                loginManager.onEncryptionResponse(event.getConnection(), packet);
            } catch (Exception e) {
                e.printStackTrace();
                loginManager.stopLogin(event.getConnection(), Text.fromPlain("Invalid encryption response"));
            }
        }
    }
}