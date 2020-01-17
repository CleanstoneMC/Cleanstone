package rocks.cleanstone.endpoint.minecraft.java.net.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.PingPacket;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.PongPacket;
import rocks.cleanstone.net.event.InboundPacketEvent;

@Slf4j
@Component
public class PingListener {
    @Async
    @EventListener
    public void onReceive(InboundPacketEvent<PingPacket> event) {
        long payload = event.getPacket().getPayload();

        event.getConnection().sendPacket(new PongPacket(payload));
    }
}