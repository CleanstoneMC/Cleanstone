package rocks.cleanstone.game.block.event;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.player.Player;

import java.util.Collection;

public class BlockBreakEvent extends CancellableEvent {
    private final Position position;
    private final Player player;
    private Block block;
    private Collection<ItemStack> drops;

    /**
     * @param block    The Block
     * @param position The BlockPosition
     * @param player   The Player
     * @param drops    The Drops
     */
    public BlockBreakEvent(Block block, Position position, Player player, Collection<ItemStack> drops) {
        this.block = block;
        this.position = position;
        this.player = player;
        this.drops = drops;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Position getPosition() {
        return position;
    }

    public Player getPlayer() {
        return player;
    }

    public Collection<ItemStack> getDrops() {
        return drops;
    }

    public void setDrops(Collection<ItemStack> drops) {
        this.drops = drops;
    }
}
