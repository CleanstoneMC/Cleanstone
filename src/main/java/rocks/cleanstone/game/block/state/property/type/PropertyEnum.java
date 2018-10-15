package rocks.cleanstone.game.block.state.property.type;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;

import java.math.RoundingMode;

public class PropertyEnum<E extends Enum<E>> extends AbstractProperty<E> {

    private final int totalValuesAmount, maxSerializationBits;
    private final Class<E> enumClass;

    public PropertyEnum(String key, Class<E> enumClass) {
        super(key);
        this.enumClass = enumClass;
        totalValuesAmount = enumClass.getEnumConstants().length;
        maxSerializationBits = IntMath.log2(totalValuesAmount, RoundingMode.CEILING);
    }

    @Override
    public int serialize(E value) {
        return value.ordinal();
    }

    @Override
    public E deserialize(int serializedValue) {
        Preconditions.checkElementIndex(serializedValue, enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[serializedValue];
    }

    @Override
    public int getTotalValuesAmount() {
        return totalValuesAmount;
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