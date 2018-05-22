package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;
import rocks.cleanstone.game.world.region.chunk.vanilla.ChunkDataPacketFactory;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbilities;
import rocks.cleanstone.net.minecraft.packet.outbound.*;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

import java.util.concurrent.ThreadLocalRandom;

public class WorldPackets {

    @Order(value = 20)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();
        Position position = new Position(0, 46, 0, null);
        Rotation rotation = new Rotation(0, 0);
        player.setEntity(new SimpleHuman((int) System.currentTimeMillis(), position, rotation));

        player.sendPacket(new JoinGamePacket(0, 1, Dimension.OVERWORLD, Difficulty.EASY, LevelType.DEFAULT, false));
        player.sendPacket(new SpawnPositionPacket(position));
        player.sendPacket(new OutPlayerAbilitiesPacket(new PlayerAbilities[]{
                PlayerAbilities.CAN_FLY, PlayerAbilities.IS_CREATIVE}, 0.4F, 0));
        player.sendPacket(new OutPlayerPositionAndLookPacket(position.getX(), position.getY(), position.getZ(), rotation.getYaw(), rotation.getPitch(), 0, ThreadLocalRandom.current().nextInt()));
        //player.sendPacket(new WindowItemsPacket(0,));

        FlatWorldGenerator flatWorldGenerator = new FlatWorldGenerator();

        for (int x = 0; x < 14; x++) {
            for (int z = 0; z < 14; z++) {
                ChunkDataPacket chunkDataPacket = ChunkDataPacketFactory.create(
                        flatWorldGenerator.generateChunk(x - 7, z - 7), true);
                player.sendPacket(chunkDataPacket);
            }
        }
    }
}
