package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.enums.Animation;
import rocks.cleanstone.net.minecraft.packet.outbound.AnimationPacket;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerAnimationEvent;

@Component
public class PlayerAnimationListener {
    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerAnimationListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerMove(PlayerAnimationEvent playerAnimationEvent) {
        if (playerAnimationEvent.isCancelled()) {
            return;
        }

        AnimationPacket animationPacket = new AnimationPacket(playerAnimationEvent.getPlayer().getEntity().getEntityID(), Animation.SWING_MAIN_ARM);

        playerManager.broadcastPacket(animationPacket, playerAnimationEvent.getPlayer());
    }
}
