package rocks.cleanstone.game.entity.vanilla;

import javax.annotation.Nullable;

import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.game.inventory.InventoryHolder;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.inventory.Hand;

public interface Human extends InventoryHolder, LivingEntity {

    @Nullable
    ItemStack getItemByHand(Hand hand);

    void setSelectedSlot(short slot);

    short getSelectedSlot();
}
