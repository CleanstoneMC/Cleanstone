package rocks.cleanstone.game.block.state.property.type;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

public class PropertyInteger extends AbstractProperty<Integer> {

    private final int maxSerializationBits;

    public PropertyInteger(String key, int minValue, int maxValue, Integer defaultValue) {
        super(key, defaultValue);
        this.maxSerializationBits = IntMath.log2(maxValue - minValue, RoundingMode.CEILING);
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
