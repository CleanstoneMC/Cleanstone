package rocks.cleanstone.data;

import javax.annotation.Nullable;

public interface KeyValueDataRepository<K,V> {

    @Nullable
    V get(K key);

    void set(K key, V value);
}
