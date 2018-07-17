package rocks.cleanstone.player.data.standard;

import java.io.Serializable;

import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.gamemode.GameMode;

public class EntityData implements Serializable {

    private static final long serialVersionUID = -45622734L;

    private final RotatablePosition logoutPosition;
    private final String logoutWorldID;
    private final GameMode gameMode;
    private final boolean flying;

    public EntityData(RotatablePosition logoutPosition, String logoutWorldID, GameMode gameMode,
                      boolean flying) {
        this.logoutPosition = logoutPosition;
        this.logoutWorldID = logoutWorldID;
        this.gameMode = gameMode;
        this.flying = flying;
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

    public boolean isFlying() {
        return flying;
    }
}
