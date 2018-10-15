package rocks.cleanstone.net.minecraft.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;

@Component
public class HandshakeListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof HandshakePacket) {
            final HandshakePacket packet = (HandshakePacket) event.getPacket();

            final ClientProtocolLayer updatedLayer = MinecraftClientProtocolLayer.byVersionNumber(packet.getVersion());
            if (updatedLayer != null) event.getConnection().setClientProtocolLayer(updatedLayer);

            final VanillaProtocolState updatedState = VanillaProtocolState.byStateID(packet.getState());
            if ((updatedState == VanillaProtocolState.STATUS || updatedState == VanillaProtocolState.LOGIN)
                    && event.getConnection().getProtocolState() == VanillaProtocolState.HANDSHAKE) {
                event.getConnection().setProtocolState(updatedState);
            }
        }
    }
}
