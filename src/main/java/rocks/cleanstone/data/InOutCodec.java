package rocks.cleanstone.data;

public interface InOutCodec<V, S> extends InboundCodec<V, S>, OutboundCodec<V, S> {
}
