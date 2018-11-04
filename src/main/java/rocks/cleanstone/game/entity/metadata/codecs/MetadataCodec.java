package rocks.cleanstone.game.entity.metadata.codecs;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

public interface MetadataCodec<T> {
    ByteBuf encode(ByteBuf byteBuf, T metadata);

    T decode(ByteBuf byteBuf) throws IOException;
}
