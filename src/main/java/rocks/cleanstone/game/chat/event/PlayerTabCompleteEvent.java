package rocks.cleanstone.game.chat.event;

import javax.annotation.Nullable;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.utils.Vector;

public class PlayerTabCompleteEvent {
    private final Player player;
    private final int transactionId;
    private final String text;
    private final Vector lookedAtBlock;

    public PlayerTabCompleteEvent(Player player, int transactionId, String text, @Nullable Vector lookedAtBlock) {
        this.player = player;
        this.transactionId = transactionId;
        this.text = text;
        this.lookedAtBlock = lookedAtBlock;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getText() {
        return text;
    }

    @Nullable
    public Vector getLookedAtBlock() {
        return lookedAtBlock;
    }
}
