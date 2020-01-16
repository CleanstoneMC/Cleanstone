package rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.VanillaEntity;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.VanillaMobType;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.cleanstone.Chicken;

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
