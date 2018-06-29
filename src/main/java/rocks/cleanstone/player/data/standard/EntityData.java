package rocks.cleanstone.player.data.standard;

import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.gamemode.GameMode;

public class EntityData {

    private final Location logoutLocation;
    private final GameMode gameMode;

    public EntityData(Location logoutLocation, GameMode gameMode) {
        this.logoutLocation = logoutLocation;
        this.gameMode = gameMode;
    }

    public Location getLogoutLocation() {
        return logoutLocation;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
