package rocks.cleanstone.data.leveldb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.nio.file.Path;
import javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import rocks.cleanstone.data.KeyValueDataRepository;

@Slf4j
public class LevelDBDataSource implements KeyValueDataRepository<ByteBuf, ByteBuf>, AutoCloseable {
    private final Path path;
    private final DB database;

    public LevelDBDataSource(Path path, Options options) throws IOException {
        this.path = path;
        if (options == null) {
            options = new Options();
        }
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
            log.error("Error occurred while closing LevelDB '" + path.getFileName() + "'", e);
        }
    }

    @Nullable
    @Override
    public ByteBuf get(ByteBuf key) {
        final val keyBytes = new byte[key.readableBytes()];
        key.readBytes(keyBytes);
        final byte[] value = database.get(keyBytes);
        return value != null ? Unpooled.wrappedBuffer(value) : null;
    }

    @Override
    public void set(ByteBuf key, ByteBuf value) {
        final byte[] keyBytes = new byte[key.readableBytes()];
        key.readBytes(keyBytes);
        if (value == null) {
            database.delete(keyBytes);
            return;
        }

        final byte[] valueBytes = new byte[value.readableBytes()];
        value.readBytes(valueBytes);
        database.put(keyBytes, valueBytes);
    }

    @Override
    public void drop() {
        final WriteBatch batch = database.createWriteBatch();

        final DBIterator iterator = database.iterator();
        iterator.seekToFirst();
        iterator.forEachRemaining(entry -> batch.delete(entry.getKey()));

        database.write(batch);
        log.info("dropped leveldb at {}", path);
    }
}
