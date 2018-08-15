package rocks.cleanstone.game.block.state.property;

import java.util.ArrayList;
import java.util.Collection;

public class PropertiesBuilder {

    protected final Collection<PropertyValuePair<?>> propertyValuePairs;

    public PropertiesBuilder() {
        propertyValuePairs = new ArrayList<>();
    }

    public PropertiesBuilder(Properties properties) {
        propertyValuePairs = new ArrayList<>(properties.getPropertyValuePairs());
    }

    public <T> PropertiesBuilder withProperty(Property<T> property, T value) {
        propertyValuePairs.add(new PropertyValuePair<>(property, value));
        return this;
    }

    public Properties create() {
        return new Properties(propertyValuePairs);
    }
}
