package rocks.cleanstone.game.material.block.vanilla;

import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.VanillaMiningLevel;

public class VanillaBlockTypes {

    private VanillaBlockTypes() {
    }

    public static void registerAll() {
        register(new SolidBlockType(VanillaMaterial.DIRT, VanillaMiningLevel.HAND));
        register(new SolidBlockType(VanillaMaterial.GRASS, VanillaMiningLevel.HAND));
        register(new SolidBlockType(VanillaMaterial.STONE, VanillaMiningLevel.WOODEN));
        register(new SolidBlockType(VanillaMaterial.CHEST, VanillaMiningLevel.HAND));
    }

    private static void register(BlockType blockType) {
        MaterialRegistry.registerBlockType(blockType.getMaterial(), blockType);
    }
}
