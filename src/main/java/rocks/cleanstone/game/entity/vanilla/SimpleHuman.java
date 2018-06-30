package rocks.cleanstone.game.entity.vanilla;

import javax.annotation.Nullable;

import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.inventory.Inventory;
import rocks.cleanstone.game.inventory.SimpleInventory;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.inventory.Hand;

public class SimpleHuman extends AbstractEntity implements Human {

    private Inventory inventory;
    private Rotation headRotation;
    private short mainHandSlot = 0;

    public SimpleHuman(Location location, Rotation headRotation) {
        super(VanillaEntityType.HUMAN, location);
        this.headRotation = headRotation;
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
    public Rotation getHeadRotation() {
        return headRotation;
    }

    @Override
    public void setHeadRotation(Rotation headRotation) {
        this.headRotation = headRotation;
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
