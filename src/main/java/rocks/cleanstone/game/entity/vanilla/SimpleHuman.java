package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.inventory.Inventory;

public class SimpleHuman extends AbstractEntity implements Human {

    private GameMode gameMode;
    private Inventory inventory;

    public SimpleHuman(int entityID, Position position) {
        super(entityID, VanillaEntityType.HUMAN, position);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
