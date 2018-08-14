package rocks.cleanstone.game.block.state.property.type;

import rocks.cleanstone.game.block.state.property.Property;

public abstract class AbstractProperty<T> implements Property<T> {

    private final String name;
    private final T defaultValue;

    public AbstractProperty(String name, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public T getDefault() {
        return defaultValue;
    }
}
