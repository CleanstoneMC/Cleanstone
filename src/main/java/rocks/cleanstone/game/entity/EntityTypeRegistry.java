package rocks.cleanstone.game.entity;

import java.util.Collection;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;

public interface EntityTypeRegistry {
    void registerEntityType(EntityType entityType, InOutCodec<? extends Entity, ByteBuf> codec);

    void unregisterEntityType(EntityType entityType);

    Collection<EntityType> getAllEntityTypes();

    @Nullable
    EntityType getEntityType(Entity entity);

    @Nullable
    EntityType getEntityType(int typeID);

    @Nullable
    InOutCodec<? extends Entity, ByteBuf> getEntityCodec(Entity entity);

    @Nullable
    InOutCodec<? extends Entity, ByteBuf> getEntityCodec(EntityType entityType);
}
