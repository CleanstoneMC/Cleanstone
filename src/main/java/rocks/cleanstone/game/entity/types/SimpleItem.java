package rocks.cleanstone.game.entity.types;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.SimpleEntity;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.world.World;

public class SimpleItem extends SimpleEntity implements Item {
    private ItemStack itemStack;

    public SimpleItem(World world, Position position, boolean persistent, boolean glowing, ItemStack itemStack) {
        super(world, position, persistent, true, glowing);
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
