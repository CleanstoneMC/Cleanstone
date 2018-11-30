package rocks.cleanstone.game.entity.cleanstone;

import javax.annotation.Nullable;

import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.game.inventory.InventoryHolder;
import rocks.cleanstone.game.inventory.item.ItemStack;

public interface Human extends InventoryHolder, LivingEntity {

    @Nullable
    ItemStack getItemByHand(Hand hand);

    short getSelectedSlot();

    void setSelectedSlot(short slot);
}
