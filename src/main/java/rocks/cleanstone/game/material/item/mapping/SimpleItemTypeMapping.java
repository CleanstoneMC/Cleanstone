package rocks.cleanstone.game.material.item.mapping;

import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.utils.SimpleIDMapping;

public class SimpleItemTypeMapping<T> extends SimpleIDMapping<ItemType, T> implements ItemTypeMapping<T> {

    public SimpleItemTypeMapping(ItemType defaultType) {
        super(defaultType);
    }

    @Override
    public ItemType getItemType(T id) {
        return getType(id);
    }
}
