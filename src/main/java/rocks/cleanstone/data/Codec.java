package rocks.cleanstone.data;

import java.io.IOException;

public interface Codec<V, S> {
    V deserialize(S data) throws IOException;

    S serialize(V value) throws IOException;
}
