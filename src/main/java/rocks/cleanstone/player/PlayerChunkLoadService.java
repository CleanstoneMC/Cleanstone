package rocks.cleanstone.player;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.UnloadChunkPacket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Component
public class PlayerChunkLoadService {
    private final Multimap<UUID, Pair<Integer, Integer>> playerHasLoaded = ArrayListMultimap.create();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Load a chunk for a player if they have not already loaded it
     *
     * @param player Player to load the chunk for
     * @param chunkX chunk x coordinate
     * @param chunkZ chunk z coordinate
     */
    public void loadChunk(Player player, int chunkX, int chunkZ) {
        UUID uuid = player.getID().getUUID();
        if (hasPlayerLoaded(uuid, chunkX, chunkZ))
            return;

        registerLoadedChunk(uuid, chunkX, chunkZ);
        sendChunkLoadPacket(player, chunkX, chunkZ);
    }

    /**
     * Mark a chunk as loaded for a given player
     * @param uuid Player uuid to mark the chunk as loaded for
     * @param chunkX chunk x coordinate
     * @param chunkZ chunk z coordinate
     */
    public void registerLoadedChunk(UUID uuid, int chunkX, int chunkZ) {
        playerHasLoaded.get(uuid).add(Pair.of(chunkX, chunkZ));
    }

    /**
     * Send a chunk data packet to the given player for the given chunk
     * @param player The player to send the chunk data packet to
     * @param chunkX chunk x coordiante
     * @param chunkZ chunk z coordinate
     */
    public void sendChunkLoadPacket(Player player, int chunkX, int chunkZ) {
        World world = player.getEntity().getWorld();

        world.getChunk(chunkX, chunkZ).addCallback(chunk -> {
            if (chunk == null) {
                logger.error("Chunk {}:{} is null", chunkX, chunkZ);
                return;
            }

            ChunkDataPacket chunkDataPacket = new ChunkDataPacket(chunkX, chunkZ, true, chunk.getBlockDataTable(), new NamedBinaryTag[]{});
            player.sendPacket(chunkDataPacket);
        }, throwable -> logger.error("Error getting Chunk", throwable));
    }

    /**
     * Unload a chunk for a player if they have loaded it
     *
     * @param player Player to unload the chunk for
     * @param chunkX chunk x coordinate
     * @param chunkZ chunk z coordinate
     */
    public void unloadChunk(Player player, int chunkX, int chunkZ) {
        UUID uuid = player.getID().getUUID();
        if (!hasPlayerLoaded(uuid, chunkX, chunkZ))
            return;

        unregisterLoadedChunk(uuid, chunkX, chunkZ);
        sendChunkUnloadPacket(player, chunkX, chunkZ);
    }

    /**
     * Mark a chunk as not loaded for a given player
     * @param uuid Player uuid to mark the chunk as not loaded for
     * @param chunkX chunk x coordinate
     * @param chunkZ chunk z coordinate
     */
    public void unregisterLoadedChunk(UUID uuid, int chunkX, int chunkZ) {
        playerHasLoaded.get(uuid).remove(Pair.of(chunkX, chunkZ));
    }

    /**
     * Unloads all chunks that are marked as loaded for the player
     *
     * @param player The player to unload the chunks for
     */
    public void unloadAllChunks(Player player) {
        UUID uuid = player.getID().getUUID();
        getLoadedChunks(uuid).forEach(chunk -> {
            int x = chunk.getLeft();
            int z = chunk.getRight();
            unregisterLoadedChunk(uuid, x, z);
            sendChunkUnloadPacket(player, x, z);
        });
    }

    /**
     * Send a chunk unload packet to the given player for the given chunk
     * @param player The player to send the chunk unload packet to
     * @param chunkX chunk x coordiante
     * @param chunkZ chunk z coordinate
     */
    public void sendChunkUnloadPacket(Player player, int chunkX, int chunkZ) {
        player.sendPacket(new UnloadChunkPacket(chunkX, chunkZ));
    }

    /**
     * Get a list of all chunk coordinates the given player has loaded
     * @param uuid The uuid of the player
     * @return Collection of chunks which are marked as loaded for the player
     */
    public Collection<Pair<Integer, Integer>> getLoadedChunks(UUID uuid) {
        return new ArrayList<>(playerHasLoaded.get(uuid));
    }

    /**
     * Checks if a given chunk is marked as loaded for the given player
     * @param uuid The player's uuid to check
     * @param chunkX chunk x coordinate
     * @param chunkZ chunk z coordinate
     * @return true if the chunk is marked as loaded, false otherwise
     */
    public boolean hasPlayerLoaded(UUID uuid, int chunkX, int chunkZ) {
        return playerHasLoaded.get(uuid).contains(Pair.of(chunkX, chunkZ));
    }

    /**
     * Marks all chunks as not loaded for the player
     * @param uuid The player's uuid
     */
    public void unregisterAllLoadedChunks(UUID uuid) {
        playerHasLoaded.removeAll(uuid);
    }
}
