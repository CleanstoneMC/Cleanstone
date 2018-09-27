package rocks.cleanstone.data;

import java.io.IOException;

public interface OutboundCodec<V, S> extends Codec {
    S encode(V value) throws IOException;
}
