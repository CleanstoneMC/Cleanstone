package rocks.cleanstone.game.entity.codec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.game.entity.SimpleLivingEntity;

@Component
public class LivingEntityCodec implements InOutCodec<SimpleLivingEntity, ByteBuf> {

    private final VersionedCodec<SimpleLivingEntity> versionedCodec;

    @Autowired
    public LivingEntityCodec(SimpleLivingEntityCodec codec) {
        versionedCodec = VersionedCodec.withMainCodec(0, codec);
    }

    @Override
    public SimpleLivingEntity decode(ByteBuf data) throws IOException {
        return versionedCodec.decode(data);
    }

    @Override
    public ByteBuf encode(SimpleLivingEntity value) throws IOException {
        return versionedCodec.encode(value);
    }
}
