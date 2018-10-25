package rocks.cleanstone.player.event;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.event.EntityMoveEvent;
import rocks.cleanstone.player.Player;

@Data
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PlayerMoveEvent extends EntityMoveEvent {
    Player player;
    MoveReason moveReason;

    public PlayerMoveEvent(Player player, HeadRotatablePosition oldPosition,
                           HeadRotatablePosition newPosition, MoveReason moveReason) {
        super(player.getEntity(), oldPosition, newPosition);
        this.player = player;
        this.moveReason = moveReason;
    }
}
