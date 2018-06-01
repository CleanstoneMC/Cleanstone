package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.packet.enums.PlayerAbilities;
import rocks.cleanstone.net.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.packet.outbound.OutPlayerAbilitiesPacket;
import rocks.cleanstone.net.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.packet.outbound.SpawnPositionPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

import java.util.concurrent.ThreadLocalRandom;

public class WorldPackets {

    @Order(value = 20)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        World world = player.getEntity().getLocation().getPosition().getWorld();
        Position playerPosition = player.getEntity().getLocation().getPosition();
        Rotation playerRotation = player.getEntity().getLocation().getRotation();

        player.sendPacket(
                new JoinGamePacket(
                        player.getEntity().getEntityID(),
                        player.getGameMode().getTypeId(),
                        world.getDimension(),
                        world.getDifficulty(),
                        world.getLevelType(),
                        false)
        );

        player.sendPacket(new SpawnPositionPacket(playerPosition));

        player.sendPacket(new OutPlayerAbilitiesPacket(
                player.getAbilities().toArray(new PlayerAbilities[0]), player.getFlyingSpeed(), 0));

        player.sendPacket(new OutPlayerPositionAndLookPacket(playerPosition.getX(), playerPosition.getY(),
                playerPosition.getZ(), playerRotation.getYaw(), playerRotation.getPitch(), 0,
                ThreadLocalRandom.current().nextInt()));

        //player.sendPacket(new WindowItemsPacket(0,));
    }
}
