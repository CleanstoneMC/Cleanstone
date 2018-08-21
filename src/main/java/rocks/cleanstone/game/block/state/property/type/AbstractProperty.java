package rocks.cleanstone.game.block.state.property.type;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractProperty)) return false;
        AbstractProperty<?> that = (AbstractProperty<?>) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
