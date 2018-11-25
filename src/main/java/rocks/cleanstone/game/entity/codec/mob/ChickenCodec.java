package rocks.cleanstone.game.entity.codec.mob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.game.entity.vanilla.mob.SimpleChicken;

@Component
public class ChickenCodec implements InOutCodec<SimpleChicken, ByteBuf> {

    private final VersionedCodec<SimpleChicken> versionedCodec;

    @Autowired
    public ChickenCodec(SimpleChickenCodec codec) {
        versionedCodec = VersionedCodec.withMainCodec(0, codec);
    }

    @Override
    public SimpleChicken decode(ByteBuf data) throws IOException {
        return versionedCodec.decode(data);
    }

    @Override
    public ByteBuf encode(SimpleChicken value) throws IOException {
        return versionedCodec.encode(value);
    }
}
