package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.game.inventory.InventoryHolder;
import rocks.cleanstone.game.inventory.item.ItemStack;

import javax.annotation.Nullable;

public interface Human extends InventoryHolder, LivingEntity {

    @Nullable
    ItemStack getItemByHand(Hand hand);

    void setSelectedSlot(short slot);

    short getSelectedSlot();
}
