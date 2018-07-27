package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;


import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

public class PlayerPositionCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        double x = byteBuf.readDouble();
        double feetY = byteBuf.readDouble();
        double z = byteBuf.readDouble();
        boolean onGround = byteBuf.readBoolean();

        Preconditions.checkArgument(Double.isFinite(x) && Double.isFinite(feetY) && Double.isFinite(z),
                "Non-finite position " + x + ":" + feetY + ":" + z);
        Preconditions.checkArgument(Math.abs(x) <= 3.2e7 && Math.abs(z) <= 3.2e7,
                "Too big position " + x + ":" + feetY + ":" + z + " (>3.2e7)");

        return new PlayerPositionPacket(x, feetY, z, onGround);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("PlayerPosition is inbound and cannot be encoded");
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
