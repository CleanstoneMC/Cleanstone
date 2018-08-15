package rocks.cleanstone.game.block.state.property.type;

public class PropertyBoolean extends AbstractProperty<Boolean> {

    public PropertyBoolean(String key, Boolean defaultValue) {
        super(key, defaultValue);
    }

    public PropertyBoolean(String key) {
        super(key, false);
    }

    @Override
    public int serialize(Boolean value) {
        // Blame Mojang for true being 0
        return value ? 0 : 1;
    }

    @Override
    public Boolean deserialize(int serializedValue) {
        return serializedValue == 0;
    }

    @Override
    public int getNeededSerializationBitAmount() {
        return 1;
    }

    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }
}
