package rocks.cleanstone.game.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SimpleEntitySerialization implements EntitySerialization {

    private final EntityTypeRegistry entityTypeRegistry;
    private final VersionedCodec<Entity> versionedCodec;

    @Autowired
    public SimpleEntitySerialization(EntityTypeRegistry entityTypeRegistry) {
        this.entityTypeRegistry = entityTypeRegistry;
        versionedCodec = VersionedCodec.withMainCodec(0, new CleanstoneEntitySerialization());
    }

    @Override
    public ByteBuf serializeEntity(Entity entity) throws IOException {
        return versionedCodec.encode(entity);
    }

    @Override
    public Entity deserializeEntity(ByteBuf data) throws IOException {
        return versionedCodec.decode(data);
    }

    private class CleanstoneEntitySerialization implements InOutCodec<Entity, ByteBuf> {
        @Override
        public ByteBuf encode(Entity entity) throws IOException {
            InOutCodec<Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entity);
            EntityType entityType = entityTypeRegistry.getEntityType(entity);
            ByteBuf data = Unpooled.buffer();

            ByteBufUtils.writeVarInt(data, entityType.getTypeID());
            ByteBuf encoded = entityCodec.encode(entity);
            data.writeBytes(encoded);
            encoded.release();

            return data;
        }

        @Override
        public Entity decode(ByteBuf data) throws IOException {
            int typeID = ByteBufUtils.readVarInt(data);

            EntityType entityType = entityTypeRegistry.getEntityType(typeID);
            InOutCodec<Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entityType);

            return entityCodec.decode(data);
        }
    }
}
