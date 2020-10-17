package rocks.cleanstone.game.entity;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

public interface EntitySerialization {

    ByteBuf serializeEntity(Entity entity) throws IOException;

    Entity deserializeEntity(ByteBuf data) throws IOException;
}
