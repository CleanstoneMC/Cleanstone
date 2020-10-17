package rocks.cleanstone.endpoint.minecraft.vanilla.item;

import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.block.VanillaBlockType;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.item.BlockItemType;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class VanillaBlockItemTypeProvider {

    private final MaterialRegistry materialRegistry;

    public VanillaBlockItemTypeProvider(MaterialRegistry materialRegistry) {
        this.materialRegistry = materialRegistry;
    }

    @PostConstruct
    public void registerVanillaItemTypes() {
        Arrays.stream(VanillaBlockType.values()).map(BlockItemType::new).forEach(materialRegistry::registerItemType);
    }

}
