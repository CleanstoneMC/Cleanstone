package rocks.cleanstone.data;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

/**
 * Codec proxy that allows the underlying main codec to be replaced without sacrificing
 * backwards-compatibility by mapping each codec to a unique hardcoded codec ID that
 * determines which codec is used during deserialization
 */
public class VersionedCodec<T> implements InOutCodec<T, ByteBuf> {

    private final InOutCodec<T, ByteBuf> mainCodec;
    private final int mainCodecID;
    private final Map<Integer, InOutCodec<T, ByteBuf>> legacyCodecMap = new HashMap<>();

    private VersionedCodec(InOutCodec<T, ByteBuf> mainCodec, int mainCodecID) {
        this.mainCodec = mainCodec;
        this.mainCodecID = mainCodecID;
    }

    public static <T> VersionedCodec<T> withMainCodec(int codecID, InOutCodec<T, ByteBuf> mainCodec) {
        return new VersionedCodec<>(mainCodec, codecID);
    }

    public VersionedCodec<T> withLegacyCodec(int codecID, InOutCodec<T, ByteBuf> legacyCodec) {
        legacyCodecMap.put(codecID, legacyCodec);
        return this;
    }

    @Override
    public T decode(ByteBuf data) throws IOException {
        int codecID = ByteBufUtils.readVarInt(data);
        if (mainCodecID == codecID) {
            return mainCodec.decode(data);
        } else {
            InOutCodec<T, ByteBuf> legacyCodec = legacyCodecMap.get(codecID);
            Preconditions.checkNotNull(legacyCodec, "There is no matching codec for ID " + codecID);
            return legacyCodec.decode(data);
        }
    }

    @Override
    public ByteBuf encode(T value) throws IOException {
        ByteBuf data = Unpooled.buffer();
        ByteBufUtils.writeVarInt(data, mainCodecID);
        ByteBuf serializedValue = mainCodec.encode(value);
        data.writeBytes(serializedValue);
        serializedValue.release();
        return data;
    }
}
