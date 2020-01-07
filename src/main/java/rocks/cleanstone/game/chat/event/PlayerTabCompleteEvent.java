package rocks.cleanstone.game.chat.event;

import rocks.cleanstone.net.minecraft.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.player.Player;

public class PlayerTabCompleteEvent {
    private final Player player;
    private final String text;

    private final InTabCompletePacket inTabCompletePacket;

    public PlayerTabCompleteEvent(InTabCompletePacket inTabCompletePacket, Player player, String text) {
        this.inTabCompletePacket = inTabCompletePacket;
        this.player = player;
        this.text = text;
    }

    public Player getPlayer() {
        return player;
    }

    public String getText() {
        return text;
    }

    public InTabCompletePacket getInTabCompletePacket() {
        return inTabCompletePacket;
    }
}
