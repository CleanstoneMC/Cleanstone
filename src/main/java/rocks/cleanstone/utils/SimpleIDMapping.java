package rocks.cleanstone.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class SimpleIDMapping<T, I> {

    private final BiMap<T, I> typeIDMap = HashBiMap.create();
    private final T defaultType;

    public SimpleIDMapping(T defaultType) {
        this.defaultType = defaultType;
    }

    public void setID(T type, I id) {
        typeIDMap.put(type, id);
    }

    public I getID(T type) {
        I defaultID = !type.equals(defaultType) ? getID(defaultType) : null;
        I id = typeIDMap.get(type);
        if (id != null) {
            return id;
        } else if (defaultID != null) {
            return defaultID;
        } else {
            throw new IllegalStateException("There is neither an id for "
                    + type + " nor for the default type");
        }
    }

    public T getType(I id) {
        return typeIDMap.inverse().getOrDefault(id, defaultType);
    }
}
