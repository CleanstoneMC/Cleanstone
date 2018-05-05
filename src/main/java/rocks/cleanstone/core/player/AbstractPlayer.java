package rocks.cleanstone.core.player;

public abstract class AbstractPlayer implements Player {

    protected final PlayerID id;

    public AbstractPlayer(PlayerID id) {
        this.id = id;
    }

    @Override
    public PlayerID getId() {
        return id;
    }
}
