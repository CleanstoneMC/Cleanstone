package rocks.cleanstone.player.data.standard;

import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.gamemode.GameMode;

public class EntityData {

    private final RotatablePosition logoutPosition;
    private final String logoutWorldID;
    private final GameMode gameMode;

    public EntityData(RotatablePosition logoutPosition, String logoutWorldID, GameMode gameMode) {
        this.logoutPosition = logoutPosition;
        this.logoutWorldID = logoutWorldID;
        this.gameMode = gameMode;
    }

    public RotatablePosition getLogoutPosition() {
        return logoutPosition;
    }

    public String getLogoutWorldID() {
        return logoutWorldID;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
