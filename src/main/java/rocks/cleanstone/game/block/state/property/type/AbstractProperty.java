package rocks.cleanstone.game.block.state.property.type;

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
}
