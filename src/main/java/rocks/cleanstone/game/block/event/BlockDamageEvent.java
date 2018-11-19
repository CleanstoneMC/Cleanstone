package rocks.cleanstone.game.block.event;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.player.Player;

public class BlockDamageEvent extends CancellableEvent {
    private final Position position;
    private final Player player;
    private Block block;

    /**
     * @param block    The Block
     * @param position The BlockPosition
     * @param player   The Player
     */
    public BlockDamageEvent(Block block, Position position, Player player) {
        this.block = block;
        this.position = position;
        this.player = player;
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
}
