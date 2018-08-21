package rocks.cleanstone.game.block.state.property;

public interface Property<T> {
    String getName();

    int serialize(T value);

    T deserialize(int serializedValue);

    int getTotalValuesAmount();

    int getNeededSerializationBitAmount();

    Class<T> getValueClass();
}
