package rocks.cleanstone.player.listener;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.minecraft.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerTeleportEvent;

@Slf4j
@Component
public class PlayerTeleportListener {
    private final PlayerManager playerManager;

    @Autowired
    public PlayerTeleportListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerTeleport(PlayerTeleportEvent playerTeleportEvent) {
        final Player player = playerTeleportEvent.getPlayer();
        final Human oldHuman = player.getEntity();
        final World world = oldHuman.getWorld();
        final Human newHuman = new SimpleHuman(world, playerTeleportEvent.getNewPosition());

        // TODO consider thread-safety with getting/changing player entities
        log.debug("{} now has entity id {}", player.getName(), newHuman.getEntityID());
        player.setEntity(newHuman);
        world.getEntityRegistry().removeEntity(oldHuman);
        world.getEntityRegistry().addEntity(newHuman);

        final DestroyEntitiesPacket destroyEntitiesPacket = new DestroyEntitiesPacket(
                Collections.singletonList(oldHuman.getEntityID()));
        playerManager.broadcastPacket(destroyEntitiesPacket);

        final OutPlayerPositionAndLookPacket playerPositionAndLookPacket = new OutPlayerPositionAndLookPacket(playerTeleportEvent.getNewPosition(), 0,
                ThreadLocalRandom.current().nextInt());
        player.sendPacket(playerPositionAndLookPacket);

        final SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(newHuman.getEntityID(),
                player.getID().getUUID(), playerTeleportEvent.getNewPosition(), null); //TODO: Add Metadata

        playerManager.broadcastPacket(spawnPlayerPacket, player);
    }
}
