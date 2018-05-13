package rocks.cleanstone.game.material;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockTypes;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.game.material.item.vanilla.VanillaItemTypes;

public class MaterialRegistry {

    private static final Map<Material, BlockType> blockMap = Maps.newConcurrentMap();
    private static final Map<Material, ItemType> itemMap = Maps.newConcurrentMap();

    static {
        VanillaBlockTypes.registerAll();
        VanillaItemTypes.registerAll();
    }

    private MaterialRegistry() {
    }

    public static void registerItemType(Material material, ItemType itemType) {
        itemMap.put(material, itemType);
    }

    public static void registerBlockType(Material material, BlockType blockType) {
        blockMap.put(material, blockType);
    }

    public static BlockType getBlockType(Material material) {
        return blockMap.get(material);
    }

    public static ItemType getItemType(Material material) {
        return itemMap.get(material);
    }

    public static Collection<ItemType> getItemTypes() {
        return itemMap.values();
    }

    public static Collection<BlockType> getBlockTypes() {
        return blockMap.values();
    }
}
