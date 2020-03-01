package rocks.cleanstone.storage.engine.leveldb.world;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.PropertyRegistry;

import static rocks.cleanstone.net.utils.ByteBufUtils.*;

@Slf4j
public class PropertyIDAssociationsCodec implements InOutCodec<PropertyIDAssociations, ByteBuf> {

    private final PropertyRegistry propertyRegistry;
    private final String worldID;

    public PropertyIDAssociationsCodec(String worldID, PropertyRegistry propertyRegistry) {
        this.propertyRegistry = propertyRegistry;
        this.worldID = worldID;
    }

    @Override
    public PropertyIDAssociations decode(ByteBuf data) throws IOException {
        Map<Property<?>, Integer> propertyIDMap = new HashMap<>();
        int propertyAmount = readVarInt(data);
        int numericID = 0;
        for (int i = 0; i < propertyAmount; i++) {
            String nameID = readUTF8(data);
            Property<?> property = propertyRegistry.getProperties().stream()
                    .filter(prop -> prop.getName().equals(nameID))
                    .findAny().orElse(null);
            numericID = readVarInt(data);

            if (property == null) {
                log.error("Cannot find property matching " + nameID + " in " + worldID);
            } else {
                propertyIDMap.put(property, numericID);
            }
        }
        return new PropertyIDAssociations(propertyIDMap, numericID);
    }

    @Override
    public ByteBuf encode(PropertyIDAssociations propertyIDAssociations) throws IOException {
        ByteBuf data = Unpooled.buffer();
        Map<Property<?>,Integer> propertyIDMap = propertyIDAssociations.getPropertyIDMap();
        int propertyAmount = propertyIDMap.keySet().size();
        writeVarInt(data, propertyAmount);
        for (Map.Entry<Property<?>, Integer> entry : propertyIDMap.entrySet()) {
            Property<?> property = entry.getKey();
            Integer numericID = entry.getValue();
            writeUTF8(data, property.getName());
            writeVarInt(data, numericID);
        }
        return data;
    }
}
