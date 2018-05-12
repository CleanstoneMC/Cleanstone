package rocks.cleanstone.game.material.item.vanilla;

import com.google.common.collect.Maps;

import java.util.Map;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockTypes;
import rocks.cleanstone.game.material.item.BlockItemType;
import rocks.cleanstone.game.material.item.ItemType;

public class VanillaItemTypes {

    private static final Map<Material, ItemType> MATERIAL_ITEM_TYPE_MAP = Maps.newConcurrentMap();

    static {
        VanillaBlockTypes.getMaterialBlockTypeMap().forEach(
                (material, blockType) -> register(material, new BlockItemType(blockType)));
    }

    private VanillaItemTypes() {
    }

    private static void register(Material material, ItemType itemType) {
        MATERIAL_ITEM_TYPE_MAP.put(material, itemType);
    }

    public static void registerAllTo(MaterialRegistry materialRegistry) {
        MATERIAL_ITEM_TYPE_MAP.forEach(materialRegistry::registerItemType);
    }

    public static Map<Material, ItemType> getMaterialItemTypeMap() {
        return MATERIAL_ITEM_TYPE_MAP;
    }
}
