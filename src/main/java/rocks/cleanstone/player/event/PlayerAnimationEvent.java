package rocks.cleanstone.player.event;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.player.Player;

public class PlayerAnimationEvent extends CancellableEvent {
    private final Player player;
    private final Hand hand;

    public PlayerAnimationEvent(Player player, Hand hand) {
        this.player = player;
        this.hand = hand;
    }

    public Player getPlayer() {
        return player;
    }

    public Hand getHand() {
        return hand;
    }
}
