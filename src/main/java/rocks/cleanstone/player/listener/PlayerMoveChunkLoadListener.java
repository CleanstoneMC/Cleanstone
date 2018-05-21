package rocks.cleanstone.player.listener;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springframework.context.event.EventListener;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.EntityMoveEvent;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;
import rocks.cleanstone.game.world.region.chunk.vanilla.ChunkDataPacketFactory;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.player.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMoveChunkLoadListener {
    private final Table<UUID, Integer, Integer> playerHasLoaded = HashBasedTable.create();
    private final FlatWorldGenerator flatWorldGenerator = new FlatWorldGenerator();

    @EventListener
    public void onPlayerMove(EntityMoveEvent entityMoveEvent) {
        if (!(entityMoveEvent.getEntity() instanceof Player)) {
            return;
        }

        final int chunkX = ((int) entityMoveEvent.getNewPosition().getX()) >> 4;
        final int chunkZ = ((int) entityMoveEvent.getNewPosition().getZ()) >> 4;

        if (isSameChunk(entityMoveEvent.getOldPosition(), entityMoveEvent.getNewPosition()) && hasPlayerLoaded(((Player) entityMoveEvent.getEntity()), chunkX, chunkZ)) {
            return;
        }

        for (int i = -8; i < 8; i++) {
            for (int j = -8; j < 8; j++) {
                if (!hasPlayerLoaded(((Player) entityMoveEvent.getEntity()), chunkX + i, chunkZ + j)) {
                    playerHasLoaded.put(((Player) entityMoveEvent.getEntity()).getId().getUUID(), chunkX + i, chunkZ + j);

                    ChunkDataPacket chunkDataPacket = ChunkDataPacketFactory.create(flatWorldGenerator.generateChunk(chunkX + i, chunkZ + j), true);
                    ((Player) entityMoveEvent.getEntity()).sendPacket(chunkDataPacket);
                }
            }
        }
    }

    private boolean isSameChunk(Position oldPosition, Position newPosition) {
        final int oldChunkX = ((int) oldPosition.getX()) >> 4;
        final int oldChunkZ = ((int) oldPosition.getZ()) >> 4;

        final int newChunkX = ((int) newPosition.getX()) >> 4;
        final int newChunkZ = ((int) newPosition.getZ()) >> 4;

        return oldChunkX == newChunkX && oldChunkZ == newChunkZ;
    }

    private boolean hasPlayerLoaded(Player player, int x, int z) {
        return playerHasLoaded.rowMap().getOrDefault(player.getId().getUUID(), new HashMap<>()).get(x) == z;
    }
}
