package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.inventory.Inventory;
import rocks.cleanstone.game.inventory.SimpleInventory;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.packet.enums.Hand;

import javax.annotation.Nullable;

public class SimpleHuman extends AbstractEntity implements Human {

    private GameMode gameMode;
    private Inventory inventory;
    private Rotation headRotation;
    private short mainHandSlot = 0;

    public SimpleHuman(Location location, Rotation headRotation) {
        super(VanillaEntityType.HUMAN, location);
        this.headRotation = headRotation;
        this.inventory = new SimpleInventory(128);//TODO: Correct Size
    }

    @Override
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Rotation getHeadRotation() {
        return headRotation;
    }

    @Override
    public void setHeadRotation(Rotation headRotation) {
        this.headRotation = headRotation;
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
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
    public void setSelectedSlot(short slot) {
        mainHandSlot = slot;
    }

    @Override
    public short getSelectedSlot() {
        return mainHandSlot;
    }

    @Override
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

}
