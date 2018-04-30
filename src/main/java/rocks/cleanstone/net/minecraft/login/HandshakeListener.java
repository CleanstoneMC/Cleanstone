package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.PacketListenerAdapter;
import rocks.cleanstone.net.minecraft.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

public class HandshakeListener extends PacketListenerAdapter {

    @Override
    public void onReceive(Packet packet, Connection connection) {
        HandshakePacket handshakePacket = (HandshakePacket) packet;

        ClientProtocolLayer updatedLayer = MinecraftClientProtocolLayer.byVersionNumber(handshakePacket.getVersion());
        if (updatedLayer != null) connection.setClientProtocolLayer(updatedLayer);

        VanillaProtocolState updatedState = VanillaProtocolState.byStateID(handshakePacket.getState());
        if (updatedState != null && updatedState != VanillaProtocolState.PLAY)
            connection.setProtocolState(updatedState);
    }
}
