package rocks.cleanstone.game.entity.vanilla;

import javax.annotation.Nullable;

import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.game.inventory.Inventory;
import rocks.cleanstone.game.inventory.SimpleInventory;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.world.World;

public class SimpleHuman extends AbstractEntity implements Human {

    private HeadRotatablePosition position;
    private Inventory inventory;
    private short mainHandSlot = 0;

    public SimpleHuman(World world, HeadRotatablePosition position) {
        super(VanillaEntityType.HUMAN, world, position);
        this.position = position;
        this.inventory = new SimpleInventory(128);//TODO: Correct Size
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public HeadRotatablePosition getPosition() {
        return position;
    }

    @Override
    public void setPosition(HeadRotatablePosition position) {
        this.position = position;
    }

    @Nullable
    @Override
    public ItemStack getItemByHand(Hand hand) {
        if (hand == Hand.MAIN_HAND) {
            return inventory.getItemStackInSlot(36 + mainHandSlot); //TODO: Remove Magic Number aka SlotID of first Slot
        }
        //TODO: Implement OffHand
        return null;
    }

    @Override
    public short getSelectedSlot() {
        return mainHandSlot;
    }

    @Override
    public void setSelectedSlot(short slot) {
        mainHandSlot = slot;
    }
}
