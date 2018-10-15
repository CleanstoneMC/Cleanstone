package rocks.cleanstone.net.minecraft.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PingPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.PongPacket;

@Slf4j
@Component
public class PingListener {
    @Async
    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof PingPacket) {
            final long payload = ((PingPacket) event.getPacket()).getPayload();

            event.getConnection().sendPacket(new PongPacket(payload));
        }
    }
}