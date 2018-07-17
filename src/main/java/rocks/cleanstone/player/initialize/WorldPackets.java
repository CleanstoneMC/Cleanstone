package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.util.concurrent.ThreadLocalRandom;

import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.packet.outbound.OutPlayerAbilitiesPacket;
import rocks.cleanstone.net.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.packet.outbound.SpawnPositionPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class WorldPackets {

    @Order(value = 20)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();
        World world = player.getEntity().getWorld();

        Dimension dimension = world != null ? world.getDimension() : Dimension.OVERWORLD;
        Difficulty difficulty = world != null ? world.getDifficulty() : Difficulty.EASY;
        LevelType levelType = world != null ? world.getLevelType() : LevelType.DEFAULT;

        RotatablePosition playerPosition = player.getEntity().getPosition();

        player.sendPacket(
                new JoinGamePacket(player.getEntity().getEntityID(), (VanillaGameMode) player.getGameMode(),
                        dimension, difficulty, levelType, false)
        );

        player.sendPacket(new SpawnPositionPacket(playerPosition));

        player.sendPacket(new OutPlayerAbilitiesPacket(
                player.getAbilities().toArray(new PlayerAbility[0]), player.getFlyingSpeed(), 0));

        player.sendPacket(new OutPlayerPositionAndLookPacket(playerPosition, 0,
                ThreadLocalRandom.current().nextInt()));

        //player.sendPacket(new WindowItemsPacket(0,));
    }
}
