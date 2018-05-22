package rocks.cleanstone.player;

import com.google.common.base.Objects;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbilities;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Player {

    protected final PlayerID id;

    protected boolean op = false;
    private Human entity;
    private GameMode gameMode = VanillaGameMode.CREATIVE;
    private List<PlayerAbilities> playerAbilities = new ArrayList<>();
    private float flyingSpeed = 0.4F;

    public AbstractPlayer(PlayerID id) {
        this.id = id;
    }

    @Override
    public PlayerID getId() {
        return id;
    }

    @Override
    public boolean isOp() {
        return op;
    }

    @Override
    public void setOp(boolean op) {
        this.op = op;
    }

    @Override
    public Human getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Human entity) {
        this.entity = entity;
    }

    @Override
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public PlayerAbilities[] getPlayerAbilities() {
        return playerAbilities.toArray(new PlayerAbilities[playerAbilities.size()]);
    }

    public void setPlayerAbilities(List<PlayerAbilities> playerAbilities) {
        this.playerAbilities = playerAbilities;
    }

    @Override
    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public void setFlyingSpeed(float flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlayer)) return false;
        AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
