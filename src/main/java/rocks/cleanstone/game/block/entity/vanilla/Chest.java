package rocks.cleanstone.game.block.entity.vanilla;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.entity.BlockEntity;
import rocks.cleanstone.game.inventory.Inventory;
import rocks.cleanstone.game.inventory.InventoryHolder;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockTypes;

public class Chest extends BlockEntity implements InventoryHolder {

    private Inventory inventory;

    public Chest(Block block) {
        super(VanillaBlockTypes.of(VanillaMaterial.CHEST));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
