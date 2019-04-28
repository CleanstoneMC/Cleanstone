package rocks.cleanstone.data;

import java.io.IOException;

/**
 * A data decoder
 */
public interface InboundCodec<V, S> extends Codec {
    V decode(S data) throws IOException;
}
