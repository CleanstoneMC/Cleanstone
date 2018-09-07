package rocks.cleanstone.game.entity;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.Codec;

import javax.annotation.Nullable;
import java.util.Collection;

public interface EntityTypeRegistry {
    void registerEntityType(EntityType entityType, Codec<Entity, ByteBuf> codec);

    void unregisterEntityType(EntityType entityType);

    Collection<EntityType> getAllEntityTypes();

    @Nullable
    Codec<Entity, ByteBuf> getEntityCodec(EntityType entityType);
}
