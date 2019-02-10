package rocks.cleanstone.net.minecraft.entity.metadata.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.cleanstone.Chicken;
import rocks.cleanstone.net.minecraft.entity.VanillaEntity;
import rocks.cleanstone.net.minecraft.entity.VanillaMobType;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;

@Component
public class ChickenConverter implements EntityConverter<Chicken> {

    private final LivingEntityConverter livingEntityConverter;

    @Autowired
    public ChickenConverter(LivingEntityConverter livingEntityConverter) {
        this.livingEntityConverter = livingEntityConverter;
    }

    @Override
    public VanillaEntity convert(Chicken entity, ProtocolEntityScheme entityScheme) {
        int typeID = entityScheme.getEntityNodeByID(VanillaMobType.CHICKEN.getName()).getTypeID();
        return new VanillaEntity(typeID, livingEntityConverter.convert(entity, entityScheme));
    }

    @Override
    public Class<? extends Entity> getEntityClass() {
        return Chicken.class;
    }
}
