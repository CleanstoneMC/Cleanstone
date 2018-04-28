package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.PacketListenerAdapter;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;

public class EncryptionResponseListener extends PacketListenerAdapter {

    private final LoginManager loginManager;

    public EncryptionResponseListener(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public void onReceive(Packet packet, Connection connection) {
        EncryptionResponsePacket encryptionResponsePacket = (EncryptionResponsePacket) packet;
        if (connection.getProtocolState() != VanillaProtocolState.LOGIN) return;

        loginManager.onEncryptionResponse(connection, encryptionResponsePacket);
    }
}