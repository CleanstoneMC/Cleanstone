package rocks.cleanstone.game.material.block.vanilla;

import com.google.common.collect.Maps;

import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.VanillaMiningLevel;

public class VanillaBlockTypes {

    private static final Map<Material, BlockType> MATERIAL_BLOCK_TYPE_MAP = Maps.newConcurrentMap();

    static {
        register(VanillaMaterial.STONE, new SolidBlockType(VanillaMaterial.STONE, VanillaMiningLevel.WOODEN));
        register(VanillaMaterial.CHEST, new SolidBlockType(VanillaMaterial.CHEST, VanillaMiningLevel.HAND));
    }

    private VanillaBlockTypes() {
    }

    private static void register(Material material, BlockType blockType) {
        MATERIAL_BLOCK_TYPE_MAP.put(material, blockType);
    }

    @Nullable
    public static BlockType of(Material material) {
        return MATERIAL_BLOCK_TYPE_MAP.get(material);
    }

    public static void registerAllTo(MaterialRegistry materialRegistry) {
        MATERIAL_BLOCK_TYPE_MAP.forEach(materialRegistry::registerBlockType);
    }

    public static Map<Material, BlockType> getMaterialBlockTypeMap() {
        return MATERIAL_BLOCK_TYPE_MAP;
    }
}
