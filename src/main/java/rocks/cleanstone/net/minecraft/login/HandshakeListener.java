package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.PacketListenerAdapter;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.minecraft.inbound.HandshakePacket;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.VanillaProtocolState;

public class HandshakeListener extends PacketListenerAdapter {

    @Override
    public void onReceive(Packet packet, Connection connection) {
        HandshakePacket handshakePacket = (HandshakePacket) packet;

        ClientProtocolLayer updatedLayer = MinecraftClientProtocolLayer.byVersionNumber(handshakePacket.getVersion());
        if (updatedLayer != null) connection.setClientProtocolLayer(updatedLayer);

        VanillaProtocolState updatedState = VanillaProtocolState.byStateID(handshakePacket.getState());
        if (updatedState != null)
            connection.setProtocolState(updatedState);
    }
}
