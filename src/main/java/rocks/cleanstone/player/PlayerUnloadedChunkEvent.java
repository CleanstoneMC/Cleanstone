package rocks.cleanstone.player;

import lombok.Data;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

@Data
public class PlayerUnloadedChunkEvent {
    private final Player player;
    private final World world;
    private final ChunkCoords coords;

    public PlayerUnloadedChunkEvent(Player player, World world, ChunkCoords coords) {
        this.player = player;
        this.world = world;
        this.coords = coords;
    }
}
