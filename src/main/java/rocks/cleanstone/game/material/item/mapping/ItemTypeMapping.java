package rocks.cleanstone.game.material.item.mapping;

import rocks.cleanstone.game.material.item.ItemType;

/**
 * Maps each ItemType to a unique ID
 */
public interface ItemTypeMapping<T> {
    T getID(ItemType itemType);

    ItemType getItemType(T id);
}
