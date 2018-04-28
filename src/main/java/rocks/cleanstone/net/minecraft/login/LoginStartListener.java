package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.PacketListenerAdapter;
import rocks.cleanstone.net.minecraft.packet.inbound.LoginStartPacket;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;

public class LoginStartListener extends PacketListenerAdapter {

    @Override
    public void onReceive(Packet packet, Connection connection) {
        LoginStartPacket loginStartPacket = (LoginStartPacket) packet;
        if (connection.getProtocolState() != VanillaProtocolState.LOGIN) return;
        String accountName = loginStartPacket.getPlayerName();

    }
}