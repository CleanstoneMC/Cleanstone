package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.InPlayerPositionAndLookPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.io.IOException;

public class PlayerPositionAndLookCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final double x = byteBuf.readDouble();
        final double y = byteBuf.readDouble();
        final double z = byteBuf.readDouble();
        final float yaw = byteBuf.readFloat();
        final float pitch = byteBuf.readFloat();
        final boolean onGround = byteBuf.readBoolean();

        Preconditions.checkArgument(Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z)
                        && Float.isFinite(yaw) && Float.isFinite(pitch),
                "Non-finite position/rotation " + x + ":" + y + ":" + z);
        Preconditions.checkArgument(Math.abs(x) <= 3.2e7 && Math.abs(z) <= 3.2e7,
                "Too big position " + x + ":" + y + ":" + z + " (>3.2e7)");

        return new InPlayerPositionAndLookPacket(x, y, z, yaw, pitch, onGround);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("PlayerPositionAndLook is inbound and cannot be encoded");
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
