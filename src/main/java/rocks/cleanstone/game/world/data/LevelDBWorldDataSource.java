package rocks.cleanstone.game.world.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nullable;

import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.game.world.region.chunk.Chunk;

public class LevelDBWorldDataSource extends LevelDBDataSource implements WorldDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public LevelDBWorldDataSource(File worldDataFolder, String worldID) throws IOException {
        super(new File(worldDataFolder, worldID));
    }



    @Nullable
    @Override
    public Chunk loadExistingChunk(int x, int y) {
        return null;
    }

    @Override
    public void saveChunk(Chunk chunk) {

    }
}
