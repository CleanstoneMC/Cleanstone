package rocks.cleanstone.game.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;

@Component
public class SimpleEntityTypeRegistry implements EntityTypeRegistry {

    private final Map<EntityType, InOutCodec<Entity, ByteBuf>> entityTypeCodecMap = Maps.newConcurrentMap();

    public SimpleEntityTypeRegistry() {
        // TODO Autowire all top-level entity codecs (Chicken,Zombie,etc;not LivingEntity) together with
        // their entityType
    }

    @Override
    public void registerEntityType(EntityType entityType, InOutCodec<Entity, ByteBuf> codec) {
        entityTypeCodecMap.put(entityType, codec);
    }

    @Override
    public void unregisterEntityType(EntityType entityType) {
        entityTypeCodecMap.remove(entityType);
    }

    @Override
    public Collection<EntityType> getAllEntityTypes() {
        return ImmutableSet.copyOf(entityTypeCodecMap.keySet());
    }

    @Override
    @Nullable
    public EntityType getEntityType(Entity entity) {
        return getAllEntityTypes().stream()
                .filter(entityType -> entityType.getEntityClass().isAssignableFrom(entity.getClass()))
                .findAny().orElse(null);
    }

    @Override
    @Nullable
    public EntityType getEntityType(int typeID) {
        return getAllEntityTypes().stream()
                .filter(entityType -> entityType.getTypeID() == typeID)
                .findAny().orElse(null);
    }

    @Nullable
    @Override
    public InOutCodec<Entity, ByteBuf> getEntityCodec(Entity entity) {
        EntityType entityType = getEntityType(entity);
        Preconditions.checkNotNull(entityType,
                "Entity codec for entity " + entity + " with non-registered entityType not found");
        return getEntityCodec(entityType);
    }

    @Nullable
    @Override
    public InOutCodec<Entity, ByteBuf> getEntityCodec(EntityType entityType) {
        return entityTypeCodecMap.get(entityType);
    }
}
