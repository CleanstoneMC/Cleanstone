package rocks.cleanstone.data;

/**
 * Both a data encoder and decoder
 */
public interface InOutCodec<V, S> extends InboundCodec<V, S>, OutboundCodec<V, S> {
}
