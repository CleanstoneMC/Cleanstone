package rocks.cleanstone.endpoint.minecraft.vanilla.net.listener.outbound;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.*;
import rocks.cleanstone.game.entity.event.EntityTrackEvent;
import rocks.cleanstone.game.entity.event.EntityUntrackEvent;
import rocks.cleanstone.game.entity.types.Human;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.utils.Vector;

import java.util.Collections;
import java.util.UUID;

@Slf4j
@Component
public class EntityTrackingListener {
    private final PlayerManager playerManager;

    @Autowired
    public EntityTrackingListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async
    @EventListener
    public void onEntityTrack(EntityTrackEvent event) {
        // TODO check if the observer uses the current protocol layer
        Player observer = playerManager.getOnlinePlayer(event.getObserver());
        if (observer == null) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof Human) {
            Player player = playerManager.getOnlinePlayer(entity);
            UUID uuid = player != null ? player.getID().getUUID() : UUID.randomUUID();
            // TODO Add EntityMetadata
            observer.sendPacket(new SpawnPlayerPacket(entity.getEntityID(), uuid, ((Human) entity).getPosition(), null));
        } else if (entity instanceof LivingEntity) {
            Position position = entity.getPosition();
            float headPitch = ((LivingEntity) entity).getPosition().getHeadRotation().getPitch();
            // TODO Is the mob UUID actually used?
            // TODO Add velocity
            observer.sendPacket(new SpawnMobPacket(entity.getEntityID(), UUID.randomUUID(),
                    new HeadRotatablePosition(new RotatablePosition(position, new Rotation(0, 0)), new Rotation(0, headPitch)),
                    new Vector(), entity
            ));
        }
    }

    @Async
    @EventListener
    public void onEntityUntrack(EntityUntrackEvent event) {
        // TODO check if the observer uses the current protocol layer
        Player observer = playerManager.getOnlinePlayer(event.getObserver());
        if (observer == null) {
            return;
        }
        Entity entity = event.getEntity();

        observer.sendPacket(new DestroyEntitiesPacket(Collections.singletonList(entity.getEntityID())));
    }
}
