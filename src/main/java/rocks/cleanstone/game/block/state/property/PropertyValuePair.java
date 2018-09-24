package rocks.cleanstone.game.block.state.property;

import com.google.common.base.Objects;

public class PropertyValuePair<T> {
    public static <T> PropertyValuePair<T> definitionOf(Property<T> property, T value) {
        return new PropertyValuePair<>(property, value);
    }

    public static PropertyValuePair<?>[] arrayOf(PropertyValuePair<?>... properties) {
        return properties;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyValuePair)) return false;
        PropertyValuePair<?> that = (PropertyValuePair<?>) o;
        return Objects.equal(property, that.property) &&
                Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(property, value);
    }

    @Override
    public String toString() {
        return "Property{" + property.getName() + "->" + value + "}";
    }
}
