package rocks.cleanstone.game.block.state.property;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class Properties {

    private final Collection<PropertyValuePair<?>> propertyValuePairs;

    public Properties(Collection<PropertyValuePair<?>> propertyValuePairs) {
        this.propertyValuePairs = propertyValuePairs;
    }

    public Properties(PropertyDefinition<?>[] properties) {
        this.propertyValuePairs = Arrays.stream(properties)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private <T> PropertyValuePair<T> convert(PropertyDefinition<T> propertyDefinition) {
        return new PropertyValuePair<>(propertyDefinition.getProperty(), propertyDefinition.getDefaultValue());
    }

    public Properties(PropertyHolder propertyHolder) {
        this(propertyHolder.getProperties());
    }

    public Properties() {
        this(Collections.emptyList());
    }


    @SuppressWarnings("unchecked")
    public <T> T get(Property<T> property) {
        Preconditions.checkNotNull(property, "property cannot be null");
        return (T) propertyValuePairs.stream()
                .filter(pair -> pair.getProperty().equals(property)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("cannot find property named " + property.getName())).getValue();
    }

    public Collection<PropertyValuePair<?>> getPropertyValuePairs() {
        return Collections.unmodifiableCollection(propertyValuePairs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Properties)) return false;
        Properties that = (Properties) o;
        return Objects.equal(propertyValuePairs, that.propertyValuePairs);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(propertyValuePairs);
    }

    @Override
    public String toString() {
        String props = propertyValuePairs.stream()
                .map(pair -> pair.getProperty().getName() + ": " + pair.getValue())
                .collect(Collectors.joining(", "));
        return "Props{" + props + "}";
    }
}
