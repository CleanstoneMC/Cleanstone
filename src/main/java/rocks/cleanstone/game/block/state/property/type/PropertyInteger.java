package rocks.cleanstone.game.block.state.property.type;

public class PropertyInteger extends AbstractProperty<Integer> {

    private final int maxSerializationBits;

    public PropertyInteger(String key, int minValue, int maxValue, Integer defaultValue) {
        super(key, defaultValue);

        int neededBits = 0;
        while (maxValue != 0) {
            neededBits++;
            maxValue >>= 1;
        }
        this.maxSerializationBits = neededBits;
    }

    @Override
    public int serialize(Integer value) {
        // TODO does minValue influence serialization?
        return value;
    }

    @Override
    public Integer deserialize(int serializedValue) {
        return serializedValue;
    }

    @Override
    public int getNeededSerializationBitAmount() {
        return maxSerializationBits;
    }

    @Override
    public Class<Integer> getValueClass() {
        return Integer.class;
    }
}
