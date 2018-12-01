package rocks.cleanstone.game.entity.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rocks.cleanstone.game.entity.EntityTypeRegistry;
import rocks.cleanstone.game.entity.cleanstone.codec.ChickenCodec;

@Component
public class CleanstoneEntityGroup {

    @Autowired
    public CleanstoneEntityGroup(EntityTypeRegistry entityTypeRegistry, ChickenCodec chickenCodec) {
        entityTypeRegistry.registerEntityType(CleanstoneEntityType.CHICKEN, chickenCodec);
    }
}
