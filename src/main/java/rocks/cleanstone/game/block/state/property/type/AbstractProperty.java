package rocks.cleanstone.game.block.state.property.type;

import com.google.common.base.Objects;
import rocks.cleanstone.game.block.state.property.Property;

public abstract class AbstractProperty<T> implements Property<T> {

    private final String name;

    public AbstractProperty(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
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
