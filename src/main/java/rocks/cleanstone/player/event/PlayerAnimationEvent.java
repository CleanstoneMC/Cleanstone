package rocks.cleanstone.player.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.player.Player;

@Value
@EqualsAndHashCode(callSuper = true)
public class PlayerAnimationEvent extends CancellableEvent {
    Player player;
    Hand hand;

    public PlayerAnimationEvent(Player player, Hand hand) {
        this.player = player;
        this.hand = hand;
    }
}
