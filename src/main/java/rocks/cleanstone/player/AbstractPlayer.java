package rocks.cleanstone.player;

import com.google.common.base.Objects;

public abstract class AbstractPlayer implements Player {

    protected final PlayerID id;

    public AbstractPlayer(PlayerID id) {
        this.id = id;
    }

    @Override
    public PlayerID getId() {
        return id;
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
