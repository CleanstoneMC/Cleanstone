package rocks.cleanstone.player.event;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.player.Player;

public class PlayerMoveEvent {
    private final Player player;
    private final Position oldPosition;
    private final Rotation oldRotation;
    private final Rotation oldHeadRotation;
    private Position newPosition;
    private Rotation newRotation;
    private Rotation newHeadRotation;

    public PlayerMoveEvent(Player player, Position oldPosition, Rotation oldRotation, Rotation oldHeadRotation,
                           Position newPosition, Rotation newRotation, Rotation newHeadRotation) {
        this.player = player;
        this.oldPosition = oldPosition;
        this.oldRotation = oldRotation;
        this.oldHeadRotation = oldHeadRotation;
        this.newPosition = newPosition;
        this.newRotation = newRotation;
        this.newHeadRotation = newHeadRotation;
    }

    public Player getPlayer() {
        return player;
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Position newPosition) {
        this.newPosition = newPosition;
    }

    public Rotation getOldRotation() {
        return oldRotation;
    }

    public Rotation getNewRotation() {
        return newRotation;
    }

    public void setNewRotation(Rotation newRotation) {
        this.newRotation = newRotation;
    }

    public Rotation getOldHeadRotation() {
        return oldHeadRotation;
    }

    public Rotation getNewHeadRotation() {
        return newHeadRotation;
    }

    public void setNewHeadRotation(Rotation newHeadRotation) {
        this.newHeadRotation = newHeadRotation;
    }
}
