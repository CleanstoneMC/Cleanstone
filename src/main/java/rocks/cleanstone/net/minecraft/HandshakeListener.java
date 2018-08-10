package rocks.cleanstone.net.minecraft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;

public class HandshakeListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof HandshakePacket) {
            HandshakePacket packet = (HandshakePacket) event.getPacket();

            ClientProtocolLayer updatedLayer = MinecraftClientProtocolLayer.byVersionNumber(packet.getVersion());
            if (updatedLayer != null) event.getConnection().setClientProtocolLayer(updatedLayer);

            VanillaProtocolState updatedState = VanillaProtocolState.byStateID(packet.getState());
            if ((updatedState == VanillaProtocolState.STATUS || updatedState == VanillaProtocolState.LOGIN)
                    && event.getConnection().getProtocolState() == VanillaProtocolState.HANDSHAKE) {
                event.getConnection().setProtocolState(updatedState);
            }
        }
    }
}
