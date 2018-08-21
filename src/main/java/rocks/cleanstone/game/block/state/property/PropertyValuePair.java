package rocks.cleanstone.game.block.state.property;

import com.google.common.base.Objects;

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
        return "Property{" + property.getProperty().getName() + "->" + value + "}";
    }
}
