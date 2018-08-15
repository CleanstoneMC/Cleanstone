package rocks.cleanstone.game.block.state.property;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class Properties {

    private final Collection<PropertyValuePair<?>> propertyValuePairs;

    public Properties(Collection<PropertyValuePair<?>> propertyValuePairs) {
        this.propertyValuePairs = propertyValuePairs;
    }

    public Properties() {
        this(Collections.emptyList());
    }

    public <T> T get(String propertyName) {
        Preconditions.checkNotNull(propertyName, "propertyName cannot be null");
        //noinspection unchecked
        return (T) propertyValuePairs.stream()
                .filter(pair -> pair.getProperty().getName().equals(propertyName)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("cannot find property named " + propertyName))
                .getValue();
    }

    public <T> T get(Property<T> property) {
        return get(property.getName());
    }

    public <T> T get(Class<T> valueClass) {
        //noinspection unchecked
        return (T) Iterables.getOnlyElement(propertyValuePairs.stream()
                .filter(pair -> pair.getProperty().getTypeClass() == valueClass)
                .collect(Collectors.toList())).getValue();
    }

    public Collection<PropertyValuePair<?>> getPropertyValuePairs() {
        return Collections.unmodifiableCollection(propertyValuePairs);
    }
}
