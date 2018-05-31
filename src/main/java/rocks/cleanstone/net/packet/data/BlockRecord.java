package rocks.cleanstone.net.packet.data;

public class BlockRecord {
    private final byte x;
    private final byte y;
    private final byte z;

    public BlockRecord(byte x, byte y, byte z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public byte getZ() {
        return z;
    }
}
