package rocks.cleanstone.net.minecraft.protocol.v1_13_2.inbound;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.material.item.mapping.ItemTypeMapping;

@Slf4j
@Component
public class CreativeInventoryActionCodec extends rocks.cleanstone.net.minecraft.protocol.v1_13_1.inbound.CreativeInventoryActionCodec {

    public CreativeInventoryActionCodec(@Qualifier("protocolItemTypeMapping_v1_13_2") ItemTypeMapping<Integer> itemTypeMapping) {
        super(itemTypeMapping);
    }
}
