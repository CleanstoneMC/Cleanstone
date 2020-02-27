package rocks.cleanstone.game.material;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.item.ItemType;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Registry for all registered Materials (BlockType and/or ItemType)
 */
@Component("materialRegistry")
public class SimpleMaterialRegistry implements MaterialRegistry {

    private final Set<BlockType> blockTypes = Sets.newConcurrentHashSet();
    private final Set<ItemType> itemTypes = Sets.newConcurrentHashSet();
    private final Set<Material> materials = Sets.newConcurrentHashSet();

    public SimpleMaterialRegistry() {
    }

    @Override
    public void registerBlockType(BlockType blockType) {
        if (!blockTypes.add(blockType)) throw new IllegalArgumentException(
                "blockType " + blockType.getID() + " is already registered");
        materials.add(blockType);
    }

    @Override
    public void unregisterBlockType(BlockType blockType) {
        if (!blockTypes.remove(blockType)) throw new IllegalArgumentException(
                "blockType " + blockType.getID() + " is not registered");
        materials.remove(blockType);
    }

    @Override
    public void registerItemType(ItemType itemType) {
        if (!itemTypes.add(itemType)) throw new IllegalArgumentException(
                "itemType " + itemType.getID() + " is already registered");
        materials.add(itemType);
    }

    @Override
    public void unregisterItemType(ItemType itemType) {
        if (!itemTypes.remove(itemType)) throw new IllegalArgumentException(
                "itemType " + itemType.getID() + " is not registered");
        materials.remove(itemType);
    }

    @Override
    public Collection<ItemType> getItemTypes() {
        return Collections.unmodifiableCollection(itemTypes);
    }

    @Override
    public Collection<BlockType> getBlockTypes() {
        return Collections.unmodifiableCollection(blockTypes);
    }

    @Override
    public Collection<Material> getMaterials() {
        return Collections.unmodifiableCollection(materials);
    }

    @Nullable
    @Override
    public BlockType getBlockTypeByItemType(ItemType itemType) {
        return blockTypes.stream()
                .filter(blockType -> blockType.getID().equals(itemType.getID()))
                .findAny().orElse(null);
    }

    @Nullable
    @Override
    public ItemType getItemTypeByBlockType(BlockType blockType) {
        return itemTypes.stream()
                .filter(itemType -> itemType.getID().equals(blockType.getID()))
                .findAny().orElse(null);
    }
}
