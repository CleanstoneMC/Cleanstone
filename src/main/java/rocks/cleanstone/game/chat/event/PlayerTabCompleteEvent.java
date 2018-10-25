package rocks.cleanstone.game.chat.event;

import javax.annotation.Nullable;
import lombok.Value;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.utils.Vector;

@Value
public class PlayerTabCompleteEvent {
    Player player;
    String text;
    @Nullable
    Vector lookedAtBlock;

    public PlayerTabCompleteEvent(Player player, String text, @Nullable Vector lookedAtBlock) {
        this.player = player;
        this.text = text;
        this.lookedAtBlock = lookedAtBlock;
    }
}
