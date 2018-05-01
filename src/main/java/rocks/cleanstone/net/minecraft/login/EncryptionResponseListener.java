package rocks.cleanstone.net.minecraft.login;

import org.springframework.context.event.EventListener;

import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.data.Chat;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;

public class EncryptionResponseListener {

    private LoginManager loginManager;

    public EncryptionResponseListener() {
    }

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof EncryptionResponsePacket) {
            EncryptionResponsePacket packet = (EncryptionResponsePacket) event.getPacket();

            try {
                loginManager.onEncryptionResponse(event.getConnection(), packet);
            } catch (Exception e) {
                e.printStackTrace();
                loginManager.stopLogin(event.getConnection(), new Chat("TODO: JSON reason"));
            }
        }
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginManager loginManager) {
        this.loginManager = loginManager;
    }
}