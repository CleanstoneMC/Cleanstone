package rocks.cleanstone.game.block.state.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class PropertiesBuilder {

    protected final Collection<PropertyValuePair<?>> propertyValuePairs;

    public PropertiesBuilder(PropertyHolder propertyHolder) {
        propertyValuePairs = Arrays.stream(propertyHolder.getProperties())
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public PropertiesBuilder(Properties properties) {
        propertyValuePairs = new ArrayList<>(properties.getPropertyValuePairs());
    }

    private <T> PropertyValuePair<T> convert(PropertyDefinition<T> propertyDefinition) {
        return new PropertyValuePair<>(propertyDefinition.getProperty(), propertyDefinition.getDefaultValue());
    }

    public <T> PropertiesBuilder withProperty(Property<T> property, T value) {
        propertyValuePairs.removeIf(pair -> pair.getProperty().getName().equals(property.getName()));
        propertyValuePairs.add(new PropertyValuePair<>(property, value));
        return this;
    }

    public Properties create() {
        return new Properties(propertyValuePairs);
    }
}
