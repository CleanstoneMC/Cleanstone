package rocks.cleanstone.endpoint.minecraft.block;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.item.BlockItemType;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class VanillaBlockTypeProvider {

    private MaterialRegistry materialRegistry;

    public VanillaBlockTypeProvider(MaterialRegistry materialRegistry) {
        this.materialRegistry = materialRegistry;
    }

    @PostConstruct
    public void registerVanillaBlockTypes() {
        Arrays.stream(VanillaBlockType.values()).forEach(materialRegistry::registerBlockType);
    }

    @PostConstruct
    public void registerVanillaBlockItemTypes() {
        Arrays.stream(VanillaBlockType.values()).map(BlockItemType::new).forEach(materialRegistry::registerItemType);
    }

}
