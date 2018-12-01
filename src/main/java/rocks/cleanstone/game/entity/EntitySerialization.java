package rocks.cleanstone.game.entity;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

public interface EntitySerialization {

    ByteBuf serializeEntity(Entity entity) throws IOException;

    Entity deserializeEntity(ByteBuf data) throws IOException;
}
