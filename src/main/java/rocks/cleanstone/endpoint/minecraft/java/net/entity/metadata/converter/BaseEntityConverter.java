package rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.converter;

import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.EntityMetadata;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.EntityMetadataBuilder;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.VanillaEntityMetadataEntryID;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.entrydata.ByteData;
import rocks.cleanstone.game.entity.Entity;

@Component
public class BaseEntityConverter implements BaseConverter<Entity> {

    @Override
    public EntityMetadata convert(Entity entity, ProtocolEntityScheme entityScheme) {
        EntityMetadataBuilder builder = new EntityMetadataBuilder(entityScheme, "entity");

        boolean onFire = false, crouched = false, sprinting = false, swimming = false, invisible = false,
                glowing = entity.isGlowing(), elytraFlying = false;
        builder.writeMetadata(VanillaEntityMetadataEntryID.ENTITY_BITS, ByteData.fromBits(onFire, crouched,
                false, sprinting, swimming, invisible, glowing, elytraFlying));

        return builder.build();
    }
}
