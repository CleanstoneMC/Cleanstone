package rocks.cleanstone.endpoint.minecraft.vanilla.block;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.property.PropertyDefinition;
import rocks.cleanstone.game.block.state.property.PropertyRegistry;
import rocks.cleanstone.game.material.block.BlockType;

import javax.annotation.PostConstruct;

@Component
public class VanillaPropertyProvider {

    private final PropertyRegistry registry;

    public VanillaPropertyProvider(PropertyRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void registerVanillaProperties() {
        for (BlockType blockType : VanillaBlockType.values()) {
            for (PropertyDefinition<?> propertyDefinition : blockType.getProperties()) {
                registry.registerProperty(propertyDefinition.getProperty());
            }
        }
    }
}
