package rocks.cleanstone.endpoint.minecraft.vanilla.v1_13_2.item;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.material.MaterialRegistry;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * A list of Cleanstone's item types
 * !! GENERATED FILE. This file was generated by gradle. !!
 */
@Component("vanillaItemTypeProvider_v1_13_2")
public class VanillaItemTypeProvider_v1_13_2 {
    private final MaterialRegistry materialRegistry;

    VanillaItemTypeProvider_v1_13_2(MaterialRegistry materialRegistry) {
        this.materialRegistry = materialRegistry;
    }

    @PostConstruct
    public void registerVanillaItemTypes() {
        Arrays.stream(VanillaItemType_v1_13_2.values()).forEach(materialRegistry::registerItemType);
    }
}