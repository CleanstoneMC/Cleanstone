package rocks.cleanstone.data;

import java.io.IOException;

import javax.annotation.Nullable;

import io.netty.util.ReferenceCountUtil;

public interface KeyValueDataRepository<K, V> {

    @Nullable
    V get(K key);

    @Nullable
    default <T> T get(K key, Codec<T, V> codec) throws IOException {
        V value = get(key);
        try {
            return value != null ? codec.deserialize(value) : null;
        } finally {
            ReferenceCountUtil.release(value);
        }
    }

    void set(K key, V value);

    default <T> void set(K key, T value, Codec<T, V> codec) throws IOException {
        V serialized = codec.serialize(value);
        try {
            set(key, serialized);
        } finally {
            ReferenceCountUtil.release(serialized);
        }
    }
}
