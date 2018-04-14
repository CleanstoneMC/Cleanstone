package rocks.cleanstone.net.netty;

import io.netty.buffer.ByteBuf;

public class InsulatedPacket {
    private final int packetId;
    private final ByteBuf data;

    public InsulatedPacket(int packetId, ByteBuf data) {
        this.packetId = packetId;
        this.data = data;
    }
}
