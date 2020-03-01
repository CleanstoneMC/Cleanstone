package rocks.cleanstone.storage.engine.leveldb.world;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;

import static rocks.cleanstone.net.utils.ByteBufUtils.*;

@Slf4j
public class BlockTypeIDAssociationsCodec implements InOutCodec<BlockTypeIDAssociations, ByteBuf> {

    private final MaterialRegistry materialRegistry;
    private final String worldID;

    public BlockTypeIDAssociationsCodec(String worldID, MaterialRegistry materialRegistry) {
        this.materialRegistry = materialRegistry;
        this.worldID = worldID;
    }

    @Override
    public BlockTypeIDAssociations decode(ByteBuf data) throws IOException {
        Map<BlockType, Integer> blockTypeIDMap = new HashMap<>();
        int blockTypeAmount = readVarInt(data);
        int numericID = 0;
        for (int i = 0; i < blockTypeAmount; i++) {
            String nameID = readUTF8(data);
            BlockType blockType = materialRegistry.getBlockTypes().stream()
                    .filter(type -> type.getID().equals(nameID))
                    .findAny().orElse(null);
            numericID = readVarInt(data);

            if (blockType == null) {
                log.error("Cannot find blockType matching " + nameID + " in " + worldID);
            } else {
                blockTypeIDMap.put(blockType, numericID);
            }
        }
        return new BlockTypeIDAssociations(blockTypeIDMap, numericID);
    }

    @Override
    public ByteBuf encode(BlockTypeIDAssociations blockTypeIDAssociations) throws IOException {
        ByteBuf data = Unpooled.buffer();
        Map<BlockType,Integer> blockTypeIDMap = blockTypeIDAssociations.getBlockTypeIDMap();
        int blockTypeAmount = blockTypeIDMap.keySet().size();
        writeVarInt(data, blockTypeAmount);
        for (Map.Entry<BlockType, Integer> entry : blockTypeIDMap.entrySet()) {
            BlockType blockType = entry.getKey();
            Integer numericID = entry.getValue();
            writeUTF8(data, blockType.getID());
            writeVarInt(data, numericID);
        }
        return data;
    }
}
