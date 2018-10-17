package rocks.cleanstone.player.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.enums.Animation;
import rocks.cleanstone.net.minecraft.packet.outbound.AnimationPacket;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerAnimationEvent;

@Component
@Slf4j
public class PlayerAnimationListener {
    private final PlayerManager playerManager;

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
