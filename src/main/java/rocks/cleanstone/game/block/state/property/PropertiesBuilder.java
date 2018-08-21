package rocks.cleanstone.game.block.state.property;

import com.google.common.collect.MoreCollectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PropertiesBuilder {

    protected final Collection<PropertyValuePair<?>> propertyValuePairs;

    public PropertiesBuilder(PropertyHolder propertyHolder) {
        propertyValuePairs = new ArrayList<>();
        Arrays.stream(propertyHolder.getProperties()).forEach(property -> {
            //noinspection unchecked
            propertyValuePairs.add(new PropertyValuePair<>(property, property.getDefault()));
        });
    }

    public PropertiesBuilder(Properties properties) {
        propertyValuePairs = new ArrayList<>(properties.getPropertyValuePairs());
    }

    public <T> PropertiesBuilder withProperty(Property<T> property, T value) {
        propertyValuePairs.removeIf(pair -> pair.getProperty().getName().equals(property.getName()));
        propertyValuePairs.add(new PropertyValuePair<>(property, value));
        return this;
    }

    public <T> PropertiesBuilder withProperty(String propertyName, T value, PropertyHolder holder) {
        //noinspection unchecked
        Property<T> property = Arrays.stream(holder.getProperties())
                .filter(prop -> prop.getName().equals(propertyName))
                .findAny().orElseThrow(IllegalArgumentException::new);
        propertyValuePairs.removeIf(pair -> pair.getProperty().getName().equals(property.getName()));
        propertyValuePairs.add(new PropertyValuePair<>(property, value));
        return this;
    }

    public <T> PropertiesBuilder withProperty(Class<T> valueClass, T value, PropertyHolder holder) {
        //noinspection unchecked
        Property<T> property = (Property<T>) propertyValuePairs.stream()
                .filter(pair -> pair.getProperty().getValueClass() == valueClass)
                .collect(MoreCollectors.onlyElement()).getProperty();
        propertyValuePairs.removeIf(pair -> pair.getProperty().getName().equals(property.getName()));
        propertyValuePairs.add(new PropertyValuePair<>(property, value));
        return this;
    }

    public Properties create() {
        return new Properties(propertyValuePairs);
    }
}
