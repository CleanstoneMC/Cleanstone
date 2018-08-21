package rocks.cleanstone.game.block.state.property;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Properties {

    private final Collection<PropertyValuePair<?>> propertyValuePairs;

    public Properties(Collection<PropertyValuePair<?>> propertyValuePairs) {
        this.propertyValuePairs = propertyValuePairs;
    }

    public Properties(PropertyDefinition[] properties) {
        Collection<PropertyValuePair<?>> propertyValuePairs = new ArrayList<>();
        Arrays.stream(properties).forEach(propertyDefinition -> {
            //noinspection unchecked
            propertyValuePairs.add(new PropertyValuePair<>(propertyDefinition.getProperty(),
                    propertyDefinition.getDefaultValue()));
        });
        this.propertyValuePairs = propertyValuePairs;
    }

    public Properties(PropertyHolder propertyHolder) {
        this(propertyHolder.getProperties());
    }

    public Properties() {
        this(Collections.emptyList());
    }


    public <T> T get(Property<T> property) {
        Preconditions.checkNotNull(property, "property cannot be null");
        //noinspection unchecked
        return (T) propertyValuePairs.stream()
                .filter(pair -> pair.getProperty().equals(property)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("cannot find property named " + property.getName()))
                .getValue();
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
        return propertyValuePairs.toString();
    }
}
