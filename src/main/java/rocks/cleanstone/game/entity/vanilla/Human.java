package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.inventory.InventoryHolder;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.packet.enums.Hand;

import javax.annotation.Nullable;

public interface Human extends InventoryHolder, LivingEntity {
    GameMode getGameMode();

    void setGameMode(GameMode gameMode);

    @Nullable
    ItemStack getItemByHand(Hand hand);

    void setSelectedSlot(short slot);

    short getSelectedSlot();
}
