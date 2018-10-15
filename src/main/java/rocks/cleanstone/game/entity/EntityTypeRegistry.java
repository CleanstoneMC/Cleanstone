package rocks.cleanstone.game.entity;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;

import javax.annotation.Nullable;
import java.util.Collection;

public interface EntityTypeRegistry {
    void registerEntityType(EntityType entityType, InOutCodec<Entity, ByteBuf> codec);

    void unregisterEntityType(EntityType entityType);

    Collection<EntityType> getAllEntityTypes();

    @Nullable
    InOutCodec<Entity, ByteBuf> getEntityCodec(EntityType entityType);
}
