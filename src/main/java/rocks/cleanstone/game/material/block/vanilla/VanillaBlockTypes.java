package rocks.cleanstone.game.material.block.vanilla;

import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.VanillaMiningLevel;

public class VanillaBlockTypes {

    private VanillaBlockTypes() {
    }

    public static void registerAll() {
//        register(new SolidBlockType(VanillaMaterial.DIRT, VanillaMiningLevel.HAND));
//        register(new SolidBlockType(VanillaMaterial.GRASS, VanillaMiningLevel.HAND));
//        register(new SolidBlockType(VanillaMaterial.CHEST, VanillaMiningLevel.HAND));
//
//        register(new SolidBlockType(VanillaMaterial.STONE, VanillaMiningLevel.WOODEN));
//
//        register(new SolidBlockType(VanillaMaterial.BEDROCK, VanillaMiningLevel.INFINITE));
//        register(new SolidBlockType(VanillaMaterial.FLOWING_LAVA, VanillaMiningLevel.INFINITE));
//        register(new SolidBlockType(VanillaMaterial.STILL_LAVA, VanillaMiningLevel.INFINITE));
//        register(new SolidBlockType(VanillaMaterial.FLOWING_WATER, VanillaMiningLevel.INFINITE));
//        register(new SolidBlockType(VanillaMaterial.STILL_WATER, VanillaMiningLevel.INFINITE));

        for (VanillaMaterial vanillaMaterial : VanillaMaterial.values()) {
            register(new SolidBlockType(vanillaMaterial, VanillaMiningLevel.HAND)); //TODO: Add Correct Types
        }
    }

    private static void register(BlockType blockType) {
        MaterialRegistry.registerBlockType(blockType.getMaterial(), blockType);
    }
}
