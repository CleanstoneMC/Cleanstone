package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.inventory.Inventory;

public class SimpleHuman extends AbstractEntity implements Human {

    private GameMode gameMode;
    private Inventory inventory;
    private Rotation headRotation;

    public SimpleHuman(Location location, Rotation headRotation) {
        super(VanillaEntityType.HUMAN, location);
        this.headRotation = headRotation;
    }

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
}
