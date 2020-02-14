package rocks.cleanstone.storage.entity;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.game.entity.SimpleLivingEntity;

import java.io.IOException;

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
