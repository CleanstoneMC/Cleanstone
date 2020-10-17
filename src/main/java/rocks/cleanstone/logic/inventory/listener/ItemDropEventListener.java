package rocks.cleanstone.logic.inventory.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.VanillaObjectType;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.EntityMetadataPacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.SpawnObjectPacket;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.types.SimpleItem;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.game.inventory.event.ItemDropEvent;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.utils.Vector;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ItemDropEventListener {

    @EventListener
    public void onItemDropEvent(ItemDropEvent itemDropEvent) {
        Position itemPos = itemDropEvent.getPosition();
        Player player = itemDropEvent.getPlayer();
        EntityRegistry entityRegistry = player.getEntity().getWorld().getEntityRegistry();

        Vector vel = itemPos.toVector().multiplyVector(new Vector(0.3, 0.3, 0.3));
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        double randOffset = 0.02;
        vel.addVector(new Vector(
                tlr.nextDouble(randOffset) - randOffset / 2,
                tlr.nextDouble(0.12),
                tlr.nextDouble(randOffset) - randOffset / 2));

        SimpleItem simpleItem = new SimpleItem(
                player.getEntity().getWorld(),
                itemPos,
                false,
                false,
                player.getEntity().getItemByHand(Hand.MAIN_HAND)
        );

        entityRegistry.addEntity(simpleItem);

        player.sendPacket(new SpawnObjectPacket(
                simpleItem.getEntityID(),
                UUID.randomUUID(),
                VanillaObjectType.ITEM_STACK,
                new RotatablePosition(itemPos, new Rotation(0, 0)),
                1,
                (short) vel.getX(),
                (short) vel.getY(),
                (short) vel.getZ()
        ));

        player.sendPacket(new EntityMetadataPacket(
                simpleItem.getEntityID(),
                simpleItem
        ));
    }
}
