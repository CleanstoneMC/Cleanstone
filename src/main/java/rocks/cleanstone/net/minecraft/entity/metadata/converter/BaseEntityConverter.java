package rocks.cleanstone.net.minecraft.entity.metadata.converter;

import org.springframework.stereotype.Component;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadata;
import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadataBuilder;
import rocks.cleanstone.net.minecraft.entity.metadata.VanillaEntityMetadataEntryID;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.ByteData;

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
