package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.PacketListenerAdapter;
import rocks.cleanstone.net.minecraft.packet.inbound.LoginStartPacket;
import rocks.cleanstone.net.packet.Packet;

public class LoginStartListener extends PacketListenerAdapter {

    private final LoginManager loginManager;

    public LoginStartListener(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public void onReceive(Packet packet, Connection connection) {
        LoginStartPacket loginStartPacket = (LoginStartPacket) packet;
        String playerName = loginStartPacket.getPlayerName();
        loginManager.startLogin(connection, playerName);
    }
}