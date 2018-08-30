package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.InAnimationPacket;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerAnimationEvent;

@Component
public class PlayerAnimationPacketListener {
    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerAnimationPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerMove(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof InAnimationPacket)) {
            return;
        }

        InAnimationPacket inAnimationPacket = (InAnimationPacket) inboundPacketEvent.getPacket();

        PlayerAnimationEvent playerAnimationEvent = new PlayerAnimationEvent(playerManager.getOnlinePlayer(inboundPacketEvent.getConnection()), inAnimationPacket.getHand());

        CleanstoneServer.publishEvent(playerAnimationEvent);
    }
}
