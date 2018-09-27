package rocks.cleanstone.data;

import java.io.IOException;

public interface InboundCodec<V, S> extends Codec {
    V decode(S data) throws IOException;
}
