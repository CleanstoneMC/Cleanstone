package rocks.cleanstone.player.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.game.entity.cleanstone.Human;
import rocks.cleanstone.game.entity.cleanstone.SimpleHuman;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerTeleportEvent;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

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
        Player player = playerTeleportEvent.getPlayer();
        Human oldHuman = player.getEntity();
        World world = oldHuman.getWorld();
        Human newHuman = new SimpleHuman(world, playerTeleportEvent.getNewPosition(), oldHuman.isGlowing(),
                oldHuman.getHealth());

        // TODO consider thread-safety with getting/changing player entities
        log.debug("{} now has entity id {}", player.getFormattedName(), newHuman.getEntityID());
        player.setEntity(newHuman);
        world.getEntityRegistry().removeEntity(oldHuman);
        world.getEntityRegistry().addEntity(newHuman);

        DestroyEntitiesPacket destroyEntitiesPacket = new DestroyEntitiesPacket(
                Collections.singletonList(oldHuman.getEntityID()));
        playerManager.broadcastPacket(destroyEntitiesPacket);

        OutPlayerPositionAndLookPacket playerPositionAndLookPacket = new OutPlayerPositionAndLookPacket(playerTeleportEvent.getNewPosition(), 0,
                ThreadLocalRandom.current().nextInt());
        player.sendPacket(playerPositionAndLookPacket);

        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(newHuman.getEntityID(),
                player.getID().getUUID(), playerTeleportEvent.getNewPosition(), null); //TODO: Add Metadata

        playerManager.broadcastPacket(spawnPlayerPacket, player);
    }
}
