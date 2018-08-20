package rocks.cleanstone.game.material.item.mapping;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import rocks.cleanstone.game.material.item.ItemType;

public class SimpleItemTypeMapping<T> implements ItemTypeMapping<T> {

    private final BiMap<ItemType, T> itemTypeIDMap = HashBiMap.create();
    private final ItemType defaultItemType;

    public SimpleItemTypeMapping(ItemType defaultItemType) {
        this.defaultItemType = defaultItemType;
    }

    public void setID(ItemType itemType, T id) {
        itemTypeIDMap.put(itemType, id);
    }

    @Override
    public T getID(ItemType itemType) {
        T defaultID = itemType != defaultItemType ? getID(defaultItemType) : null;
        T id = itemTypeIDMap.get(itemType);
        if (id != null) {
            return id;
        } else if (defaultID != null) {
            return defaultID;
        } else {
            throw new IllegalStateException("There is neither an id for "
                    + itemType + " nor for the default itemType");
        }
    }

    @Override
    public ItemType getItemType(T id) {
        return itemTypeIDMap.inverse().getOrDefault(id, defaultItemType);
    }
}
