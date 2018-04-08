package rocks.cleanstone.core.player;

public abstract class AbstractPlayer implements Player {

    protected final PlayerId id;

    public AbstractPlayer(PlayerId id) {
        this.id = id;
    }

    @Override
    public PlayerId getId() {
        return id;
    }
}
