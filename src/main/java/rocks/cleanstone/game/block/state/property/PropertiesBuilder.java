package rocks.cleanstone.game.block.state.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PropertiesBuilder {

    protected final Collection<PropertyValuePair<?>> propertyValuePairs;

    public PropertiesBuilder(PropertyHolder propertyHolder) {
        propertyValuePairs = new ArrayList<>();
        Arrays.stream(propertyHolder.getProperties()).forEach(propertyDefinition -> {
            //noinspection unchecked
            propertyValuePairs.add(new PropertyValuePair(propertyDefinition.getProperty(),
                    propertyDefinition.getDefaultValue()));
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

    public Properties create() {
        return new Properties(propertyValuePairs);
    }
}
