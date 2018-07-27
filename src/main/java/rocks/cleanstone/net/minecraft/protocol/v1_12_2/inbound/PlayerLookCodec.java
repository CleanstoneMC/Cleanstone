package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.PlayerLookPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.io.IOException;

public class PlayerLookCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final float yaw = byteBuf.readFloat();
        final float pitch = byteBuf.readFloat();
        final boolean onGround = byteBuf.readBoolean();

        Preconditions.checkArgument(Float.isFinite(yaw) && Float.isFinite(pitch),
                "Non-finite rotation " + yaw + ":" + pitch);

        return new PlayerLookPacket(yaw, pitch, onGround);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("PlayerLook is inbound and cannot be encoded");
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
