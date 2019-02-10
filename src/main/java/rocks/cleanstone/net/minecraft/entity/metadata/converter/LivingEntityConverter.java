package rocks.cleanstone.net.minecraft.entity.metadata.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadata;
import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadataBuilder;
import rocks.cleanstone.net.minecraft.entity.metadata.VanillaEntityMetadataEntryID;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.FloatData;

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
