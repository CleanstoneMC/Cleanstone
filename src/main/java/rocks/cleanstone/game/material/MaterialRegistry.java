package rocks.cleanstone.game.material;

import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.item.ItemType;

public interface MaterialRegistry {
    void registerBlockType(BlockType blockType);

    void unregisterBlockType(BlockType blockType);

    void registerItemType(ItemType itemType);

    void unregisterItemType(ItemType itemType);

    Collection<ItemType> getItemTypes();

    Collection<BlockType> getBlockTypes();

    Collection<Material> getMaterials();

    @Nullable
    BlockType getBlockTypeByItemType(ItemType itemType);

    @Nullable
    ItemType getItemTypeByBlockType(BlockType blockType);
}
