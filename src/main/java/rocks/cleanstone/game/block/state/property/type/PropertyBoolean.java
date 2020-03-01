package rocks.cleanstone.game.block.state.property.type;

import com.google.common.base.Preconditions;

public class PropertyBoolean extends AbstractProperty<Boolean> {

    public PropertyBoolean(String key) {
        super(key);
    }

    @Override
    public int serialize(Boolean value) {
        Preconditions.checkNotNull(value);
        // Blame Mojang for true being 0
        return value ? 0 : 1;
    }

    @Override
    public Boolean deserialize(int serializedValue) {
        Preconditions.checkPositionIndex(0, 1);
        return serializedValue == 0;
    }

    @Override
    public int getTotalValuesAmount() {
        return 2;
    }

    @Override
    public int getNeededSerializationBitAmount() {
        return 1;
    }

    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }

    @Override
    public int getTypeID() {
        return 0;
    }
}
