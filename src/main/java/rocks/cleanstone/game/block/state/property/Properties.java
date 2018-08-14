package rocks.cleanstone.game.block.state.property;

import com.google.common.collect.Maps;

import java.util.Map;

public class Properties {

    private final Map<Property<?>, ?> propertyValueMap = Maps.newConcurrentMap();

    public Properties() {
        
    }

}
