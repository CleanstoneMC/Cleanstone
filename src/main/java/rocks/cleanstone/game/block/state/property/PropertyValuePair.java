package rocks.cleanstone.game.block.state.property;

public class PropertyValuePair<T> {

    private final Property<T> property;
    private final T value;

    public PropertyValuePair(Property<T> property, T value) {
        this.property = property;
        this.value = value;
    }

    public Property<T> getProperty() {
        return property;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Property{" + property.getName() + "->" + value + "}";
    }
}
