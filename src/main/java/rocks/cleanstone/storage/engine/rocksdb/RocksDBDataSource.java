package rocks.cleanstone.storage.engine.rocksdb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.rocksdb.*;
import rocks.cleanstone.data.KeyValueDataRepository;

import javax.annotation.Nullable;
import java.nio.file.Path;

/**
 * LevelDB data source which supports key-value based IO
 */
@Slf4j
public class RocksDBDataSource implements KeyValueDataRepository<ByteBuf, ByteBuf>, AutoCloseable {
    private final Path path;
    private final RocksDB database;

    static {
        RocksDB.loadLibrary();
    }

    public RocksDBDataSource(Path path, Options options) throws RocksDBException {
        this.path = path;
        if (options == null) {
            options = new Options();
        }
        options.setCreateIfMissing(true);
        log.info("Opening Database: " + path.toFile().getPath());
        database = RocksDB.open(options, path.toFile().getPath());
    }

    public RocksDBDataSource(Path path) throws RocksDBException {
        this(path, null);
    }

    @Override
    public void close() {
        try {
            database.closeE();
        } catch (RocksDBException e) {
            log.error("Error occurred while closing LevelDB '" + path.getFileName() + "'", e);
        }
    }

    @SneakyThrows
    @Nullable
    @Override
    public ByteBuf get(ByteBuf key) {
        byte[] keyBytes = new byte[key.readableBytes()];
        key.readBytes(keyBytes);
        byte[] value = database.get(keyBytes);
        return value != null ? Unpooled.wrappedBuffer(value) : null;
    }

    @SneakyThrows
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

    @SneakyThrows
    @Override
    public void drop() {
        WriteOptions options = new WriteOptions();
        WriteBatch batch = new WriteBatch();

        RocksIterator iterator = database.newIterator();
        iterator.seekToFirst();
        for (iterator.seekToFirst(); iterator.isValid(); iterator.next()) {
            batch.delete(iterator.key());
        }

        database.write(options, batch);
        log.info("dropped rocksdb at {}", path);
    }
}
