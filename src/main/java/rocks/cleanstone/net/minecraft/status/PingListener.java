package rocks.cleanstone.net.minecraft.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PingPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.PongPacket;

public class PingListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof PingPacket) {
            final long payload = ((PingPacket) event.getPacket()).getPayload();

            event.getConnection().sendPacket(new PongPacket(payload));
        }
    }
}