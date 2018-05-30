package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.inventory.Inventory;

public class SimpleHuman extends AbstractEntity implements Human {

    private GameMode gameMode;
    private Inventory inventory;

    public SimpleHuman(Location location) {
        super(VanillaEntityType.HUMAN, location);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
