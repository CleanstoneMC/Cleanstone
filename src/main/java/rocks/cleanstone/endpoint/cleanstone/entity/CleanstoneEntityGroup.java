package rocks.cleanstone.endpoint.cleanstone.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.cleanstone.entity.codec.SimpleChickenCodec;
import rocks.cleanstone.game.entity.EntityTypeRegistry;

@Component
public class CleanstoneEntityGroup {

    @Autowired
    public CleanstoneEntityGroup(EntityTypeRegistry entityTypeRegistry, SimpleChickenCodec chickenCodec) {
        entityTypeRegistry.registerEntityType(CleanstoneEntityType.CHICKEN, chickenCodec);
    }
}
