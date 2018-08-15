package rocks.cleanstone.game.block.state.property.type;

public class PropertyEnum<E extends Enum<E>> extends AbstractProperty<E> {

    private final int maxSerializationBits;
    private final Class<E> enumClass;

    public PropertyEnum(String key, E defaultValue) {
        super(key, defaultValue);
        enumClass = defaultValue.getDeclaringClass();
        int maxValue = enumClass.getEnumConstants().length - 1, neededBits = 0;
        while (maxValue != 0) {
            neededBits++;
            maxValue >>= 1;
        }
        this.maxSerializationBits = neededBits;
    }

    @Override
    public int serialize(E value) {
        return value.ordinal();
    }

    @Override
    public E deserialize(int serializedValue) {
        return enumClass.getEnumConstants()[serializedValue];
    }

    @Override
    public int getNeededSerializationBitAmount() {
        return maxSerializationBits;
    }

    @Override
    public Class<E> getValueClass() {
        return enumClass;
    }
}