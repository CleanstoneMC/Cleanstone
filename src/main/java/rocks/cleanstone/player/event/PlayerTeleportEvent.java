package rocks.cleanstone.player.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.player.Player;

@Value
@EqualsAndHashCode(callSuper = true)
public class PlayerTeleportEvent extends PlayerMoveEvent {
    Player player;
    MoveReason moveReason;

    public PlayerTeleportEvent(Player player, HeadRotatablePosition oldPosition,
                               HeadRotatablePosition newPosition, MoveReason moveReason) {
        super(player, oldPosition, newPosition, moveReason);
        this.player = player;
        this.moveReason = moveReason;
    }
}
