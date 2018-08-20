package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import rocks.cleanstone.game.material.item.mapping.SimpleItemTypeMapping;
import rocks.cleanstone.game.material.item.vanilla.VanillaItemType;

public class ProtocolItemTypeMapping extends SimpleItemTypeMapping<Integer> {
    public ProtocolItemTypeMapping() {
        super(VanillaItemType.STONE);
        // TODO register legacy IDs
    }
}
