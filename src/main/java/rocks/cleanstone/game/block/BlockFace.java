package rocks.cleanstone.game.block;

public enum BlockFace {
    NORTH, EAST, SOUTH, WEST, UP, DOWN;

    public BlockFace getOpposite() {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return null;
        }
    }
}