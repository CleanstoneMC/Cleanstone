package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.world.region.chunk.ArrayChunkTable;
import rocks.cleanstone.game.world.region.chunk.ChunkTable;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;
import rocks.cleanstone.game.world.region.chunk.vanilla.ChunkDataPacketFactory;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbilities;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.OutPlayerAbilitiesPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPositionPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class GeneralWorldPackets {

    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();
        player.sendPacket(new JoinGamePacket(0, 1, Dimension.OVERWORLD, Difficulty.EASY, LevelType.DEFAULT, false));
        player.sendPacket(new SpawnPositionPacket(new Position(0, 46, 0, null)));
        player.sendPacket(new OutPlayerAbilitiesPacket(new PlayerAbilities[]{
                PlayerAbilities.CAN_FLY, PlayerAbilities.IS_CREATIVE}, 0.4F, 0));
        player.sendPacket(new OutPlayerPositionAndLookPacket(0, 46, 0, 0, 0, 0, ThreadLocalRandom.current().nextInt()));
        //player.sendPacket(new WindowItemsPacket(0,));
        ChunkTable chunkTable = new ArrayChunkTable();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunkTable.setBlock(x, 45, z, ImmutableBlock.of(VanillaMaterial.GRASS));
                for (int y = 44; y > 30; y--)
                    chunkTable.setBlock(x, y, z, ImmutableBlock.of(VanillaMaterial.STONE));
            }
        }
        for (int x = 0; x < 14; x++) {
            for (int z = 0; z < 14; z++) {
                ChunkDataPacket chunkDataPacket = ChunkDataPacketFactory.create(
                        new SimpleChunk(Collections.emptySet(), chunkTable, x - 7, z - 7), true);
                player.sendPacket(chunkDataPacket);
            }
        }
    }
}
