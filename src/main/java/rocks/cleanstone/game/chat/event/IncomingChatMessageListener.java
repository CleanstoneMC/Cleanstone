package rocks.cleanstone.game.chat.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.ChatMessagePacket;

public class IncomingChatMessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    @Async
    public void onChatMessage(InboundPacketEvent inboundPacketEvent) {
        if (inboundPacketEvent.getPacket() instanceof ChatMessagePacket) {
            logger.info("{}: {}", "INSERT USERNAME HERE", ((ChatMessagePacket) inboundPacketEvent.getPacket()).getMessage()); //TODO
        }
    }
}
