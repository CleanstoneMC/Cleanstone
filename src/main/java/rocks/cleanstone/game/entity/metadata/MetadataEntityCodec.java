package rocks.cleanstone.game.entity.metadata;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.entity.Entity;

import java.io.IOException;

public class MetadataEntityCodec implements InOutCodec<Entity, ByteBuf> {
    @Override
    public Entity decode(ByteBuf data) throws IOException {
        // TODO
        return null;
    }

    @Override
    public ByteBuf encode(Entity value) throws IOException {
        // TODO
        return null;
    }
}
