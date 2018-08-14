package rocks.cleanstone.game.material;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Nullable;

import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.material.item.BlockItemType;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.game.material.item.vanilla.VanillaItemType;

/**
 * Registry for all registered Materials (BlockType and/or ItemType)
 */
public class SimpleMaterialRegistry implements MaterialRegistry {

    private final Set<BlockType> blockTypes = Sets.newConcurrentHashSet();
    private final Set<ItemType> itemTypes = Sets.newConcurrentHashSet();
    private final Set<Material> materials = Sets.newConcurrentHashSet();

    public SimpleMaterialRegistry() {
        Arrays.stream(VanillaBlockType.values()).forEach(blockType -> {
            registerBlockType(blockType);
            registerItemType(new BlockItemType(blockType));
        });
        Arrays.stream(VanillaItemType.values()).forEach(this::registerItemType);
    }

    @Override
    public void registerBlockType(BlockType blockType) {
        if (!blockTypes.add(blockType)) throw new IllegalArgumentException(
                "blockType " + blockType.getMinecraftID() + " is already registered");
        materials.add(blockType);
    }

    @Override
    public void unregisterBlockType(BlockType blockType) {
        if (!blockTypes.remove(blockType)) throw new IllegalArgumentException(
                "blockType " + blockType.getMinecraftID() + " is not registered");
        materials.remove(blockType);
    }

    @Override
    public void registerItemType(ItemType itemType) {
        if (!itemTypes.add(itemType)) throw new IllegalArgumentException(
                "itemType " + itemType.getMinecraftID() + " is already registered");
        materials.add(itemType);
    }

    @Override
    public void unregisterItemType(ItemType itemType) {
        if (!itemTypes.remove(itemType)) throw new IllegalArgumentException(
                "itemType " + itemType.getMinecraftID() + " is not registered");
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
}
