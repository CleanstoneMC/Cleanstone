package rocks.cleanstone.data.leveldb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.nio.file.Path;
import javax.annotation.Nullable;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.data.KeyValueDataRepository;

public class LevelDBDataSource implements KeyValueDataRepository<ByteBuf, ByteBuf>, AutoCloseable {

    private final Path path;
    private final DB database;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public LevelDBDataSource(Path path, Options options) throws IOException {
        this.path = path;
        if (options == null) options = new Options();
        options.createIfMissing(true);
        database = JniDBFactory.factory.open(path.toFile(), options);
    }

    public LevelDBDataSource(Path path) throws IOException {
        this(path, null);
    }

    @Override
    public void close() {
        try {
            database.close();
        } catch (IOException e) {
            logger.error("Error occurred while closing LevelDB '" + path.getFileName() + "'", e);
        }
    }

    @Nullable
    @Override
    public ByteBuf get(ByteBuf key) {
        byte[] keyBytes = new byte[key.readableBytes()];
        key.readBytes(keyBytes);
        byte[] value = database.get(keyBytes);
        return value != null ? Unpooled.wrappedBuffer(value) : null;
    }

    @Override
    public void set(ByteBuf key, ByteBuf value) {
        byte[] keyBytes = new byte[key.readableBytes()];
        key.readBytes(keyBytes);
        if (value == null) {
            database.delete(keyBytes);
            return;
        }

        byte[] valueBytes = new byte[value.readableBytes()];
        value.readBytes(valueBytes);
        database.put(keyBytes, valueBytes);
    }
}
