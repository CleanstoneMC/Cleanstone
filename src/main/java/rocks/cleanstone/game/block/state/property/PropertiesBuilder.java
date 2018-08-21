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
            propertyValuePairs.add(new PropertyValuePair<>(property, property.getDefaultValue()));
        });
    }

    public PropertiesBuilder(Properties properties) {
        propertyValuePairs = new ArrayList<>(properties.getPropertyValuePairs());
    }

    public <T> PropertiesBuilder withProperty(PropertyDefinition<T> propertyDefinition, T value) {
        propertyValuePairs.removeIf(pair -> pair.getPropertyDefinition().getProperty().getName().equals(propertyDefinition.getProperty().getName()));
        propertyValuePairs.add(new PropertyValuePair<>(propertyDefinition, value));
        return this;
    }

    public <T> PropertiesBuilder withProperty(String propertyName, T value, PropertyHolder holder) {
        //noinspection unchecked
        PropertyDefinition<T> propertyDefinition = Arrays.stream(holder.getProperties())
                .filter(prop -> prop.getProperty().getName().equals(propertyName))
                .findAny().orElseThrow(IllegalArgumentException::new);
        propertyValuePairs.removeIf(pair -> pair.getPropertyDefinition().getProperty().getName().equals(propertyDefinition.getProperty().getName()));
        propertyValuePairs.add(new PropertyValuePair<>(propertyDefinition, value));
        return this;
    }

    public <T> PropertiesBuilder withProperty(Class<T> valueClass, T value, PropertyHolder holder) {
        //noinspection unchecked
        PropertyDefinition<T> propertyDefinition = (PropertyDefinition<T>) propertyValuePairs.stream()
                .filter(pair -> pair.getPropertyDefinition().getProperty().getValueClass() == valueClass)
                .collect(MoreCollectors.onlyElement()).getPropertyDefinition();
        propertyValuePairs.removeIf(pair -> pair.getPropertyDefinition().getProperty().getName().equals(propertyDefinition.getProperty().getName()));
        propertyValuePairs.add(new PropertyValuePair<>(propertyDefinition, value));
        return this;
    }

    public Properties create() {
        return new Properties(propertyValuePairs);
    }
}
