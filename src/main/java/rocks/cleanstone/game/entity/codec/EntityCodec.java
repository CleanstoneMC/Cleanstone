package rocks.cleanstone.game.entity.codec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.game.entity.SimpleEntity;

@Component
public class EntityCodec implements InOutCodec<SimpleEntity, ByteBuf> {

    private final VersionedCodec<SimpleEntity> versionedCodec;

    @Autowired
    public EntityCodec(SimpleEntityCodec codec) {
        versionedCodec = VersionedCodec.withMainCodec(0, codec);
    }

    @Override
    public SimpleEntity decode(ByteBuf data) throws IOException {
        return versionedCodec.decode(data);
    }

    @Override
    public ByteBuf encode(SimpleEntity value) throws IOException {
        return versionedCodec.encode(value);
    }
}
