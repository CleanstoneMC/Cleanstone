package rocks.cleanstone.player;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.UnloadChunkPacket;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@Component
public class PlayerChunkLoadService {
    private final Multimap<UUID, ChunkCoords> playerHasLoaded = ArrayListMultimap.create();

    /**
     * Load a chunk for a player if they have not already loaded it
     *
     * @param player Player to load the chunk for
     * @param coords chunk coordinates
     */
    public void loadChunk(Player player, ChunkCoords coords) {
        UUID uuid = player.getID().getUUID();
        if (hasPlayerLoaded(uuid, coords)) {
            return;
        }

        registerLoadedChunk(uuid, coords);
        sendChunkLoadPacket(player, coords);
    }

    /**
     * Mark a chunk as loaded for a given player
     *
     * @param uuid   Player uuid to mark the chunk as loaded for
     * @param coords chunk coordinates
     */
    public void registerLoadedChunk(UUID uuid, ChunkCoords coords) {
        playerHasLoaded.get(uuid).add(coords);
    }

    /**
     * Send a chunk data packet to the given player for the given chunk
     *
     * @param player The player to send the chunk data packet to
     * @param coords chunk coordinates
     */
    public void sendChunkLoadPacket(Player player, ChunkCoords coords) {
        World world = player.getEntity().getWorld();

        world.getChunk(coords).addCallback(chunk -> {
            if (chunk == null) {
                log.error("Chunk {} is null", coords);
                return;
            }

            ChunkDataPacket chunkDataPacket = new ChunkDataPacket(coords.getX(), coords.getZ(), true,
                    chunk.getBlockDataStorage(), new NamedBinaryTag[]{});
            player.sendPacket(chunkDataPacket);
        }, throwable -> log.error("Error getting Chunk " + coords, throwable));
    }

    /**
     * Unload a chunk for a player if they have loaded it
     *
     * @param player Player to unload the chunk for
     * @param coords chunk coordinates
     */
    public void unloadChunk(Player player, ChunkCoords coords) {
        UUID uuid = player.getID().getUUID();
        if (!hasPlayerLoaded(uuid, coords)) {
            return;
        }

        unregisterLoadedChunk(uuid, coords);
        sendChunkUnloadPacket(player, coords);
    }

    /**
     * Mark a chunk as not loaded for a given player
     *
     * @param uuid   Player uuid to mark the chunk as not loaded for
     * @param coords chunk coordinates
     */
    public void unregisterLoadedChunk(UUID uuid, ChunkCoords coords) {
        playerHasLoaded.get(uuid).remove(coords);
    }

    /**
     * Unloads all chunks that are marked as loaded for the player
     *
     * @param player The player to unload the chunks for
     */
    public void unloadAllChunks(Player player) {
        UUID uuid = player.getID().getUUID();
        getLoadedChunkCoords(uuid).forEach(coords -> {
            unregisterLoadedChunk(uuid, coords);
            sendChunkUnloadPacket(player, coords);
        });
    }

    /**
     * Send a chunk unload packet to the given player for the given chunk
     *
     * @param player The player to send the chunk unload packet to
     * @param coords chunk coordinates
     */
    public void sendChunkUnloadPacket(Player player, ChunkCoords coords) {
        player.sendPacket(new UnloadChunkPacket(coords.getX(), coords.getZ()));
    }

    /**
     * Get a list of all chunk coordinates the given player has loaded
     *
     * @param uuid The uuid of the player
     * @return Collection of chunk coords which are marked as loaded for the player
     */
    public Collection<ChunkCoords> getLoadedChunkCoords(UUID uuid) {
        return new ArrayList<>(playerHasLoaded.get(uuid));
    }

    /**
     * Checks if a given chunk is marked as loaded for the given player
     *
     * @param uuid   The player's uuid to check
     * @param coords chunk coordinates
     * @return true if the chunk is marked as loaded, false otherwise
     */
    public boolean hasPlayerLoaded(UUID uuid, ChunkCoords coords) {
        return playerHasLoaded.get(uuid).contains(coords);
    }

    /**
     * Marks all chunks as not loaded for the player
     *
     * @param uuid The player's uuid
     */
    public void unregisterAllLoadedChunks(UUID uuid) {
        playerHasLoaded.removeAll(uuid);
    }
}
