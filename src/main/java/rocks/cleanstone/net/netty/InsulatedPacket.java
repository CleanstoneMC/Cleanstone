package rocks.cleanstone.net.netty;

import io.netty.buffer.ByteBuf;

public class InsulatedPacket {
    private final int packetID;
    private final ByteBuf data;

    public InsulatedPacket(int packetID, ByteBuf data) {
        this.packetID = packetID;
        this.data = data;
    }
}
