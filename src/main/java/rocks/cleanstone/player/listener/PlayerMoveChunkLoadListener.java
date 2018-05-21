package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.PlayerMoveEvent;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;
import rocks.cleanstone.game.world.region.chunk.vanilla.ChunkDataPacketFactory;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.UnloadChunkPacket;
import rocks.cleanstone.player.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerMoveChunkLoadListener {
    private final Map<String, Map<Integer, Set<Integer>>> playerHasLoaded = new HashMap<>();

    private final FlatWorldGenerator flatWorldGenerator = new FlatWorldGenerator(); //TODO: Get correct Generator

    @EventListener
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        final int chunkX = ((int) playerMoveEvent.getNewPosition().getX()) >> 4;
        final int chunkZ = ((int) playerMoveEvent.getNewPosition().getZ()) >> 4;
        final Player player = playerMoveEvent.getPlayer();
        String uuid = player.getId().getUUID().toString();

        if (isSameChunk(playerMoveEvent.getOldPosition(), playerMoveEvent.getNewPosition()) && hasPlayerLoaded(uuid, chunkX, chunkZ)) {
            return;
        }

        for (int i = -8; i < 8; i++) {
            for (int j = -8; j < 8; j++) {
                final int currentX = chunkX + i;
                final int currentZ = chunkZ + j;

                if (i < -4 || i > 4 || j < -4 || j > 4) {
                    if (hasPlayerLoaded(uuid, currentX, currentZ)) {
                        playerUnload(uuid, currentX, currentZ);

                        UnloadChunkPacket unloadChunkPacket = new UnloadChunkPacket(currentX, currentZ);
                        player.sendPacket(unloadChunkPacket);
                    }

                    continue;
                }

                if (!hasPlayerLoaded(uuid, currentX, currentZ)) {
                    playerLoad(uuid, currentX, currentZ);

                    ChunkDataPacket chunkDataPacket = ChunkDataPacketFactory.create(flatWorldGenerator.generateChunk(currentX, currentZ), true);
                    player.sendPacket(chunkDataPacket);
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

    private void playerLoad(String uuid, int x, int z) {
        checkMap(uuid, x, z);

        playerHasLoaded.get(uuid).get(x).add(z);
    }

    private void playerUnload(String uuid, int x, int z) {
        checkMap(uuid, x, z);

        playerHasLoaded.get(uuid).get(x).remove(z);
    }

    private boolean hasPlayerLoaded(String uuid, int x, int z) {
        checkMap(uuid, x, z);

        return playerHasLoaded.get(uuid).get(x).contains(z);
    }

    private void checkMap(String uuid, int x, int z) {
        playerHasLoaded.putIfAbsent(uuid, new HashMap<>());
        playerHasLoaded.get(uuid).putIfAbsent(x, new HashSet<>());
    }
}
