package rocks.cleanstone.net.packet.data;

public class BlockChange {
    private final byte horizontalPosition;
    private final byte verticalPosition;
    private final int blockID;

    public BlockChange(byte horizontalPosition, byte verticalPosition, int blockID) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.blockID = blockID;
    }

    public BlockChange(int x, int y, byte verticalPosition, int blockID) {
        this.horizontalPosition = (byte) ((x << 4 & 15) + y); //TODO: Is this correct?
        this.verticalPosition = verticalPosition;
        this.blockID = blockID;
    }

    public byte getHorizontalPosition() {
        return horizontalPosition;
    }

    public byte getVerticalPosition() {
        return verticalPosition;
    }

    public int getBlockID() {
        return blockID;
    }
}
