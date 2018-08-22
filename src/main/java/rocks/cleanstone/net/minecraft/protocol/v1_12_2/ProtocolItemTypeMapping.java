package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import rocks.cleanstone.game.material.item.mapping.SimpleItemTypeMapping;
import rocks.cleanstone.game.material.item.vanilla.VanillaItemType;

import static rocks.cleanstone.game.material.item.vanilla.VanillaItemType.*;

public class ProtocolItemTypeMapping extends SimpleItemTypeMapping<Integer> {
    public ProtocolItemTypeMapping() {
        super(VanillaItemType.STONE);

        // TODO use damage value to prevent item metadata from getting lost

        setID(AIR, 0);
        setID(STONE, 1);
        setID(WHITE_WOOL, 35);
        setID(BLUE_WOOL, 35);
        // ...
    }
}
