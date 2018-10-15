package rocks.cleanstone.game.world.chunk.data.entity;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.InboundCodec;
import rocks.cleanstone.data.OutboundCodec;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.EntityType;
import rocks.cleanstone.game.entity.EntityTypeRegistry;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityDataCodec implements InOutCodec<EntityData, ByteBuf> {

    private final EntityTypeRegistry entityTypeRegistry;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public EntityDataCodec(EntityTypeRegistry entityTypeRegistry) {
        this.entityTypeRegistry = entityTypeRegistry;
    }

    @Override
    public EntityData decode(ByteBuf data) throws IOException {
        final Set<Entity> entities = new HashSet<>();
        int entityAmount = ByteBufUtils.readVarInt(data);
        while (entityAmount-- != 0) {
            final int entityTypeID = ByteBufUtils.readVarInt(data);
            final EntityType entityType = entityTypeRegistry.getAllEntityTypes().stream()
                    .filter(type -> type.getTypeID() == entityTypeID).findAny().orElse(null);
            if (entityType == null) {
                logger.error("Cannot find entityType with ID " + entityTypeID);
                continue;
            }
            final InboundCodec<? extends Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entityType);
            Preconditions.checkNotNull(entityCodec);
            entities.add(entityCodec.decode(data));
        }
        return new EntityData(entities);
    }

    @Override
    public ByteBuf encode(EntityData entityData) throws IOException {
        final ByteBuf data = Unpooled.buffer();
        final Collection<Entity> entities = entityData.getEntities().stream()
                .filter(Entity::isPersistent).collect(Collectors.toSet());
        ByteBufUtils.writeVarInt(data, entities.size());
        for (final Entity entity : entities) {
            ByteBufUtils.writeVarInt(data, entity.getType().getTypeID());
            final OutboundCodec<Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entity.getType());
            Preconditions.checkNotNull(entityCodec, "Cannot encode unregistered entityType " + entity.getType());
            data.writeBytes(entityCodec.encode(entity));
        }
        return data;
    }
}
