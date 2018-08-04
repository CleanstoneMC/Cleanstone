package rocks.cleanstone.player;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.apache.commons.lang3.tuple.Pair;

public class PlayerChunkLoadService {
    private final Multimap<UUID, Pair<Integer, Integer>> playerHasLoaded = ArrayListMultimap.create();

    public void playerLoad(UUID uuid, int chunkX, int chunkY) {
        playerHasLoaded.get(uuid).add(Pair.of(chunkX, chunkY));
    }

    public void playerUnload(UUID uuid, int chunkX, int chunkY) {
        playerHasLoaded.get(uuid).remove(Pair.of(chunkX, chunkY));
    }

    public Collection<Pair<Integer, Integer>> loadedChunks(UUID uuid) {
        return new ArrayList<>(playerHasLoaded.get(uuid));
    }

    public boolean hasPlayerLoaded(UUID uuid, int chunkX, int chunkY) {
        return playerHasLoaded.get(uuid).contains(Pair.of(chunkX, chunkY));
    }

    public void unloadAll(UUID uuid) {
        playerHasLoaded.removeAll(uuid);
    }
}
