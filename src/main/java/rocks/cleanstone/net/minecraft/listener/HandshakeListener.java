package rocks.cleanstone.net.minecraft.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;

@Slf4j
@Component
public class HandshakeListener {
    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof HandshakePacket) {
            HandshakePacket packet = (HandshakePacket) event.getPacket();

            ClientProtocolLayer updatedLayer = MinecraftClientProtocolLayer.byVersionNumber(packet.getVersion());
            if (updatedLayer != null) {
                event.getConnection().setClientProtocolLayer(updatedLayer);
            }

            VanillaProtocolState updatedState = VanillaProtocolState.byStateID(packet.getState());
            if ((updatedState == VanillaProtocolState.STATUS || updatedState == VanillaProtocolState.LOGIN)
                    && event.getConnection().getProtocolState() == VanillaProtocolState.HANDSHAKE) {
                event.getConnection().setProtocolState(updatedState);
            }
        }
    }
}
