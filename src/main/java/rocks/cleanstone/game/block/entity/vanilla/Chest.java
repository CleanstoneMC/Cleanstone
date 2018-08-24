package rocks.cleanstone.game.block.entity.vanilla;

import rocks.cleanstone.game.block.entity.BlockEntity;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.inventory.Inventory;
import rocks.cleanstone.game.inventory.InventoryHolder;

public class Chest extends BlockEntity implements InventoryHolder {

    private Inventory inventory;

    public Chest(BlockState state) {
        super(state);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
