package rocks.cleanstone.game.block.state.property;

public class PropertyValuePair<T> {

    private final PropertyDefinition<T> property;
    private final T value;

    public PropertyValuePair(PropertyDefinition<T> property, T value) {
        this.property = property;
        this.value = value;
    }

    public PropertyDefinition<T> getPropertyDefinition() {
        return property;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Property{" + property.getProperty().getName() + "->" + value + "}";
    }
}
