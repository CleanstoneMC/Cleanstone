package rocks.cleanstone.game.material;

import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.item.ItemType;

import java.util.Collection;

public interface MaterialRegistry {
    void registerBlockType(BlockType blockType);

    void unregisterBlockType(BlockType blockType);

    void registerItemType(ItemType itemType);

    void unregisterItemType(ItemType itemType);

    Collection<ItemType> getItemTypes();

    Collection<BlockType> getBlockTypes();

    Collection<Material> getMaterials();
}
