package rocks.cleanstone.player.event;

import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.player.Player;

public class PlayerMoveEvent {
    private final Player player;
    private final HeadRotatablePosition oldPosition;
    private HeadRotatablePosition newPosition;

    public PlayerMoveEvent(Player player, HeadRotatablePosition oldPosition, HeadRotatablePosition newPosition) {
        this.player = player;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public Player getPlayer() {
        return player;
    }

    public HeadRotatablePosition getOldPosition() {
        return oldPosition;
    }

    public HeadRotatablePosition getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(HeadRotatablePosition newPosition) {
        this.newPosition = newPosition;
    }
}
