package rocks.cleanstone.game.inventory.event;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.player.Player;

public class ItemDropEvent {
    private final Position position;
    private final Player player;
    private final ItemStack item;

    /**
     * @param position The Position of the Drop
     * @param player   The Player who dropped
     * @param item     The Item the Player dropped
     */
    public ItemDropEvent(Position position, Player player, ItemStack item) {
        this.position = position;
        this.player = player;
        this.item = item;
    }

    public Position getPosition() {
        return position;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }
}
