package rocks.cleanstone.game.block.state.mapping;

import com.google.common.base.Preconditions;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.game.material.item.mapping.ItemTypeMapping;
import rocks.cleanstone.utils.SimpleIDMapping;

public class LegacyMaterialMapping implements BlockStateMapping<Integer>, ItemTypeMapping<Integer> {

    private final SimpleIDMapping<Material, Integer> materialMapping;

    public LegacyMaterialMapping(Material defaultMaterial) {
        materialMapping = new SimpleIDMapping<>(defaultMaterial);
    }

    public void setID(Material material, int typeID) {
        byte metadata = (byte) 0;
        int id = typeID << 4 | (metadata & 0xF);
        materialMapping.setID(material, id);
    }

    public void setID(Material material, int typeID, int metadata) {
        Preconditions.checkArgument(metadata < 16, "metadata out of range");
        int id = typeID << 4 | (metadata & 0xF);
        materialMapping.setID(material, id);
    }

    public ItemType getItemType(int typeID, int metadata) {
        return getItemType(typeID << 4 | (metadata & 0xF));
    }

    @Override
    public Integer getID(BlockState state) {
        return materialMapping.getID(state.getBlockType());
    }

    @Override
    public Integer getID(ItemType itemType) {
        return materialMapping.getID(itemType);
    }

    @Override
    public BlockState getState(Integer id) {
        return BlockState.of((BlockType) materialMapping.getType(id));
    }

    @Override
    public ItemType getItemType(Integer id) {
        return (ItemType) materialMapping.getType(id);
    }
}
