package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.player.Player;

public class PlayerMoveEvent {
    private final Player player;
    private final Position oldPosition;
    private final Rotation oldRotation;
    private Position newPosition;
    private Rotation newRotation;

    public PlayerMoveEvent(Player player, Position oldPosition, Rotation oldRotation, Position newPosition, Rotation newRotation) {
        this.player = player;
        this.oldPosition = oldPosition;
        this.oldRotation = oldRotation;
        this.newPosition = newPosition;
        this.newRotation = newRotation;
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
}
