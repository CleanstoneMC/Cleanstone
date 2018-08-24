package rocks.cleanstone.game.block.state.property;

public class PropertyDefinition<T> {
    private final Property<T> property;
    private final T value;

    private PropertyDefinition(Property<T> property, T defaultValue) {
        this.property = property;
        this.value = defaultValue;
    }

    public Property<T> getProperty() {
        return property;
    }

    public T getDefaultValue() {
        return value;
    }

    public static <M> PropertyDefinition definitionOf(Property<M> property, M value) {
        return new PropertyDefinition<>(property, value);
    }

    public static PropertyDefinition[] arrayOf(PropertyDefinition... properties) {
        return properties;
    }
}
