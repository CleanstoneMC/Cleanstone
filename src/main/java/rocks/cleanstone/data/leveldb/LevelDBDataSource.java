package rocks.cleanstone.data.leveldb;

import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.KeyValueDataRepository;

public class LevelDBDataSource implements KeyValueDataRepository<ByteBuf, ByteBuf>, AutoCloseable {

    private final File file;
    private final DB database;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public LevelDBDataSource(File file, Options options) throws IOException {
        this.file = file;
        if (options == null) options = new Options();
        options.createIfMissing(true);
        database = JniDBFactory.factory.open(file, options);
    }

    public LevelDBDataSource(File file) throws IOException {
        this(file, null);
    }

    @Override
    public void close() {
        try {
            database.close();
        } catch (IOException e) {
            logger.error("Error occurred while closing LevelDB '" + file.getName() + "'", e);
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
