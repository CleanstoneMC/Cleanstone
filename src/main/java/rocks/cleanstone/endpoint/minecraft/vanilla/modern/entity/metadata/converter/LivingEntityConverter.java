package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.EntityMetadata;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.EntityMetadataBuilder;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.VanillaEntityMetadataEntryID;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.entrydata.FloatData;
import rocks.cleanstone.game.entity.LivingEntity;

@Component
public class LivingEntityConverter implements BaseConverter<LivingEntity> {

    private final BaseEntityConverter entityConverter;

    @Autowired
    public LivingEntityConverter(BaseEntityConverter entityConverter) {
        this.entityConverter = entityConverter;
    }

    @Override
    public EntityMetadata convert(LivingEntity entity, ProtocolEntityScheme entityScheme) {
        EntityMetadata entityMetadata = entityConverter.convert(entity, entityScheme);
        EntityMetadataBuilder builder = new EntityMetadataBuilder(entityScheme, "living");

        builder.writeMetadata(VanillaEntityMetadataEntryID.HEALTH, FloatData.of(entity.getHealth()));

        return new EntityMetadata(entityMetadata, builder.build());
    }
}
