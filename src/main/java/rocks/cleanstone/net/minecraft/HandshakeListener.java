package rocks.cleanstone.net.minecraft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

public class HandshakeListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof HandshakePacket) {
            HandshakePacket packet = (HandshakePacket) event.getPacket();

            ClientProtocolLayer updatedLayer = MinecraftClientProtocolLayer.byVersionNumber(packet.getVersion());
            if (updatedLayer != null) event.getConnection().setClientProtocolLayer(updatedLayer);

            VanillaProtocolState updatedState = VanillaProtocolState.byStateID(packet.getState());
            if (updatedState != null && updatedState != VanillaProtocolState.PLAY) {
                event.getConnection().setProtocolState(updatedState);
                logger.info("Received Handshake and set protocolState to " + updatedState);
            }
        }
    }
}
