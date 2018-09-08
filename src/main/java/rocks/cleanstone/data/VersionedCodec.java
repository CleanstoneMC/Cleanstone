package rocks.cleanstone.data;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Codec proxy that allows the underlying main codec to be replaced without sacrificing
 * backwards-compatibility by mapping legacy codecs to a unique codec ID that
 * determines which codec is used during deserialization
 */
public class VersionedCodec<T> implements Codec<T, ByteBuf> {

    private final Codec<T, ByteBuf> mainCodec;
    private final int mainCodecID;
    private final Map<Integer, Codec<T, ByteBuf>> legacyCodecMap = new HashMap<>();

    private VersionedCodec(Codec<T, ByteBuf> mainCodec, int mainCodecID) {
        this.mainCodec = mainCodec;
        this.mainCodecID = mainCodecID;
    }

    public static <T> VersionedCodec<T> withMainCodec(int codecID, Codec<T, ByteBuf> mainCodec) {
        return new VersionedCodec<>(mainCodec, codecID);
    }

    public VersionedCodec<T> withLegacyCodec(int codecID, Codec<T, ByteBuf> legacyCodec) {
        legacyCodecMap.put(codecID, legacyCodec);
        return this;
    }

    @Override
    public T deserialize(ByteBuf data) throws IOException {
        int codecID = ByteBufUtils.readVarInt(data);
        if (mainCodecID == codecID) {
            return mainCodec.deserialize(data);
        } else {
            Codec<T, ByteBuf> legacyCodec = legacyCodecMap.get(codecID);
            Preconditions.checkNotNull(legacyCodec, "There is no matching codec for ID " + codecID);
            return legacyCodec.deserialize(data);
        }
    }

    @Override
    public ByteBuf serialize(T value) throws IOException {
        ByteBuf data = Unpooled.buffer();
        ByteBufUtils.writeVarInt(data, mainCodecID);
        ByteBuf serializedValue = mainCodec.serialize(value);
        data.writeBytes(serializedValue);
        serializedValue.release();
        return data;
    }
}
