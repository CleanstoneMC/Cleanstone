package rocks.cleanstone.game.world.chunk;

import com.google.common.base.Preconditions;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataStorage;
import rocks.cleanstone.game.world.chunk.data.entity.EntityData;

import java.util.Collection;

public class SimpleChunk implements Chunk {

    private final BlockDataTable blockDataTable;
    private final BlockDataStorage blockDataStorage;
    private final EntityData entityData;
    private final int x, z;
    private boolean hasUnsavedChanges = false;
    // TODO biome state

    public SimpleChunk(BlockDataTable blockDataTable, BlockDataStorage blockDataStorage,
                       EntityData entityData, int x, int z) {
        this.blockDataTable = blockDataTable;
        this.blockDataStorage = blockDataStorage;
        this.entityData = entityData;
        this.x = x;
        this.z = z;
    }

    @Override
    public Collection<Block> getBlocks() {
        return blockDataTable.getBlocks();
    }

    @Override
    public Collection<Entity> getEntities() {
        return entityData.getEntities();
    }

    @Override
    public EntityData getEntityData() {
        return entityData;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        validateRelativeBlockCoordinates(x, y, z);
        return blockDataTable.getBlock(x, y, z);
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        validateRelativeBlockCoordinates(x, y, z);
        blockDataTable.setBlock(x, y, z, block);
        // TODO run the following expensive operation async
        blockDataStorage.setBlockState(x, y, z, block.getState());
        hasUnsavedChanges = true;
    }

    @Override
    public byte getBlockLight(int x, int y, int z) {
        validateRelativeBlockCoordinates(x, y, z);
        return blockDataTable.getBlockLight(x, y, z);
    }

    @Override
    public void setBlockLight(int x, int y, int z, byte blockLight) {
        validateRelativeBlockCoordinates(x, y, z);
        blockDataTable.setBlockLight(x, y, z, blockLight);
        blockDataStorage.setBlockLight(x, y, z, blockLight);
        hasUnsavedChanges = true;
    }

    @Override
    public byte getSkyLight(int x, int y, int z) {
        validateRelativeBlockCoordinates(x, y, z);
        return blockDataTable.getSkyLight(x, y, z);
    }

    @Override
    public void setSkyLight(int x, int y, int z, byte skyLight) {
        validateRelativeBlockCoordinates(x, y, z);
        if (!hasSkylight()) return;
        blockDataTable.setSkyLight(x, y, z, skyLight);
        blockDataStorage.setSkyLight(x, y, z, skyLight);
        hasUnsavedChanges = true;
    }

    @Override
    public boolean hasSkylight() {
        return blockDataTable.hasSkylight();
    }

    @Override
    public boolean hasUnsavedChanges() {
        return hasUnsavedChanges;
    }

    @Override
    public void setHasUnsavedChanges(boolean hasUnsavedChanges) {
        this.hasUnsavedChanges = hasUnsavedChanges;
    }

    @Override
    public BlockDataTable getBlockDataTable() {
        return blockDataTable;
    }

    @Override
    public BlockDataStorage getBlockDataStorage() {
        return blockDataStorage;
    }

    private void validateRelativeBlockCoordinates(int x, int y, int z) {
        Preconditions.checkArgument(
                x >= 0 && x < Chunk.WIDTH && y >= 0 && y < Chunk.HEIGHT && z >= 0 && z < Chunk.WIDTH,
                "Relative block coordinates out of bounds (" + x + ":" + y + ":" + z + ")");
    }
}
