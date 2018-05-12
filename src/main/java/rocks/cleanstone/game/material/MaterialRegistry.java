package rocks.cleanstone.game.material;

import com.google.common.collect.Maps;

import java.util.Map;

import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockTypes;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.game.material.item.vanilla.VanillaItemTypes;

public class MaterialRegistry {

    private final Map<Material, BlockType> blockMap = Maps.newConcurrentMap();
    private final Map<Material, ItemType> itemMap = Maps.newConcurrentMap();

    public MaterialRegistry() {
        // register vanilla types
        VanillaBlockTypes.registerAllTo(this);
        VanillaItemTypes.registerAllTo(this);
    }

    public void registerItemType(Material material, ItemType itemType) {
        itemMap.put(material, itemType);
    }

    public void registerBlockType(Material material, BlockType blockType) {
        blockMap.put(material, blockType);
    }

    public BlockType getBlockType(Material material) {
        return blockMap.get(material);
    }

    public ItemType getItemType(Material material) {
        return itemMap.get(material);
    }
}
