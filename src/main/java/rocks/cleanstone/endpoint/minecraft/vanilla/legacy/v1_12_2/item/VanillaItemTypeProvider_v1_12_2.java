package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.item;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.material.MaterialRegistry;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component()
public class VanillaItemTypeProvider_v1_12_2 {

    private MaterialRegistry materialRegistry;

    public VanillaItemTypeProvider_v1_12_2(MaterialRegistry materialRegistry) {
        this.materialRegistry = materialRegistry;
    }

    @PostConstruct
    public void registerVanillaItemTypes() {
        Arrays.stream(VanillaItemType_v1_12_2.values()).forEach(materialRegistry::registerItemType);
    }

}
