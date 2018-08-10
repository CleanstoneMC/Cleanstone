package rocks.cleanstone.net.minecraft.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.PingPacket;
import rocks.cleanstone.net.packet.outbound.PongPacket;

public class PingListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof PingPacket) {
            final long payload = ((PingPacket) event.getPacket()).getPayload();

            event.getConnection().sendPacket(new PongPacket(payload));
        }
    }
}