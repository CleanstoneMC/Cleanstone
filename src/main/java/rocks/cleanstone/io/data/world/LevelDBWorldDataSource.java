package rocks.cleanstone.io.data.world;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.region.chunk.Chunk;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

public class LevelDBWorldDataSource implements WorldDataSource, AutoCloseable {

    private static final String WORLD_DATA_PATH ="";

    private final String worldID;

    private DB database;

    public LevelDBWorldDataSource(String worldID) {
        this.worldID = worldID;
    }

    public void open() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);
        database = factory.open(new File(WORLD_DATA_PATH+""), options);

    }

    @Override
    public void close() throws IOException {
        database.close();
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
