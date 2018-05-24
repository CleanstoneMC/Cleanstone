package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.minecraft.packet.outbound.*;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

import java.util.concurrent.ThreadLocalRandom;

public class WorldPackets {

    @Order(value = 20)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        World world = player.getEntity().getPosition().getWorld();
        Position playerPosition = player.getEntity().getPosition();
        Rotation playerRotation = player.getEntity().getRotation();


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

        player.sendPacket(new OutPlayerAbilitiesPacket(player.getPlayerAbilities(), player.getFlyingSpeed(), 0));

        player.sendPacket(new OutPlayerPositionAndLookPacket(playerPosition.getX(), playerPosition.getY(), playerPosition.getZ(), playerRotation.getYaw(), playerRotation.getPitch(), 0, ThreadLocalRandom.current().nextInt()));

        //player.sendPacket(new WindowItemsPacket(0,));

//        FlatWorldGenerator flatWorldGenerator = new FlatWorldGenerator();
//
//        for (int x = 0; x < 14; x++) {
//            for (int z = 0; z < 14; z++) {
//                ChunkDataPacket chunkDataPacket = ChunkDataPacketFactory.create(
//                        flatWorldGenerator.generateChunk(x - 7, z - 7), true);
//                player.sendPacket(chunkDataPacket);
//            }
//        }
    }
}
