package rocks.cleanstone.game.block.event;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.player.Player;

public class BlockPlaceEvent {
    private Block block;
    private Position position;
    private Player player;
    private Face blockFace;

    public BlockPlaceEvent(Block block, Position position, Player player, Face blockFace) {
        this.block = block;
        this.position = position;
        this.player = player;
        this.blockFace = blockFace;
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

    public void setPosition(Position position) {
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Face getBlockFace() {
        return blockFace;
    }

    public void setBlockFace(Face blockFace) {
        this.blockFace = blockFace;
    }
}
