package rocks.cleanstone.endpoint.minecraft.item;

import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.block.VanillaBlockType;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.item.BlockItemType;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class VanillaItemTypeProvider {

    private MaterialRegistry materialRegistry;

    public VanillaItemTypeProvider(MaterialRegistry materialRegistry) {
        this.materialRegistry = materialRegistry;
    }

    @PostConstruct
    public void registerVanillaItemTypes() {
        Arrays.stream(VanillaBlockType.values()).map(BlockItemType::new).forEach(materialRegistry::registerItemType);
    }

}
