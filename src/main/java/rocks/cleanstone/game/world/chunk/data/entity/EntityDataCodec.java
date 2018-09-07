package rocks.cleanstone.game.world.chunk.data.entity;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.EntityType;
import rocks.cleanstone.game.entity.EntityTypeRegistry;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EntityDataCodec implements Codec<EntityData, ByteBuf> {

    private final EntityTypeRegistry entityTypeRegistry;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public EntityDataCodec(EntityTypeRegistry entityTypeRegistry) {
        this.entityTypeRegistry = entityTypeRegistry;
    }

    @Override
    public EntityData deserialize(ByteBuf data) throws IOException {
        Set<Entity> entities = new HashSet<>();
        int entityAmount = ByteBufUtils.readVarInt(data);
        while (entityAmount-- != 0) {
            int entityTypeID = ByteBufUtils.readVarInt(data);
            EntityType entityType = entityTypeRegistry.getAllEntityTypes().stream()
                    .filter(type -> type.getTypeID() == entityTypeID).findAny().orElse(null);
            if (entityType == null) {
                logger.error("Cannot find entityType with ID " + entityTypeID);
                continue;
            }
            Codec<? extends Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entityType);
            Preconditions.checkNotNull(entityCodec);
            entities.add(entityCodec.deserialize(data));
        }
        return new EntityData(entities);
    }

    @Override
    public ByteBuf serialize(EntityData entityData) throws IOException {
        ByteBuf data = Unpooled.buffer();
        ByteBufUtils.writeVarInt(data, entityData.getEntities().size());
        for (Entity entity : entityData.getEntities()) {
            ByteBufUtils.writeVarInt(data, entity.getType().getTypeID());
            Codec<Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entity.getType());
            Preconditions.checkNotNull(entityCodec, "Cannot serialize unregistered entityType " + entity.getType());
            data.writeBytes(entityCodec.serialize(entity));
        }
        return data;
    }
}
