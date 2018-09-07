package rocks.cleanstone.game.entity.metadata;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.game.entity.Entity;

import java.io.IOException;

public class MetadataEntityCodec implements Codec<Entity, ByteBuf> {
    @Override
    public Entity deserialize(ByteBuf data) throws IOException {
        // TODO
        return null;
    }

    @Override
    public ByteBuf serialize(Entity value) throws IOException {
        // TODO
        return null;
    }
}
