package rocks.cleanstone.game.block.state.mapping;

import com.google.common.base.Preconditions;
import rocks.cleanstone.endpoint.minecraft.vanilla.legacy.state.mapping.IDMetadataPair;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.game.material.item.mapping.ItemTypeMapping;
import rocks.cleanstone.utils.SimpleIDMapping;

public class LegacyMaterialMapping implements BlockStateMapping<Integer>, ItemTypeMapping<Integer> {

    private final SimpleIDMapping<Material, IDMetadataPair> materialMapping;

    public LegacyMaterialMapping(Material defaultMaterial) {
        materialMapping = new SimpleIDMapping<>(defaultMaterial);
    }

    public void setID(Material material, int typeID) {
        materialMapping.setID(material, new IDMetadataPair(typeID, 0));
    }

    public void setID(Material material, int typeID, int metadata) {
        Preconditions.checkArgument(metadata < 16, "metadata out of range");
        materialMapping.setID(material, new IDMetadataPair(typeID, metadata));
    }

    @Override
    public Integer getID(BlockState state) {
        return materialMapping.getID(state.getBlockType()).encode();
    }

    @Override
    public Integer getID(ItemType itemType) {
        return materialMapping.getID(itemType).encode();
    }

    @Override
    public BlockState getState(Integer id) {
        return BlockState.of((BlockType) materialMapping.getType(IDMetadataPair.decode(id)));
    }

    @Override
    public ItemType getItemType(Integer id) {
        return (ItemType) materialMapping.getType(IDMetadataPair.decode(id));
    }
}
