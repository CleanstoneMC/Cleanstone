package rocks.cleanstone.game.material.item.vanilla;

import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.item.BlockItemType;
import rocks.cleanstone.game.material.item.ItemType;

public class VanillaItemTypes {

    private VanillaItemTypes() {
    }

    public static void registerAll() {
        MaterialRegistry.getBlockTypes().forEach((blockType) -> register(new BlockItemType(blockType)));
    }

    private static void register(ItemType itemType) {
        MaterialRegistry.registerItemType(itemType.getMaterial(), itemType);
    }
}
