package rocks.cleanstone.data;

import java.io.IOException;

/**
 * A data encoder
 */
public interface OutboundCodec<V, S> extends Codec {
    S encode(V value) throws IOException;
}
