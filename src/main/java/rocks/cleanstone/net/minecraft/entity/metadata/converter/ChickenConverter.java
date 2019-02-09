package rocks.cleanstone.net.minecraft.entity.metadata.converter;

import org.springframework.stereotype.Component;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.cleanstone.Chicken;
import rocks.cleanstone.net.minecraft.entity.VanillaEntity;
import rocks.cleanstone.net.minecraft.entity.VanillaMobType;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;

@Component
public class ChickenConverter implements EntityConverter<Chicken> {

    private final LivingEntityConverter livingEntityConverter;

    public ChickenConverter(LivingEntityConverter livingEntityConverter) {
        this.livingEntityConverter = livingEntityConverter;
    }

    @Override
    public VanillaEntity convert(Chicken entity, ProtocolEntityScheme entityScheme) {
        return new VanillaEntity(VanillaMobType.CHICKEN, livingEntityConverter.convert(entity, entityScheme));
    }

    @Override
    public Class<? extends Entity> getEntityClass() {
        return Chicken.class;
    }
}
