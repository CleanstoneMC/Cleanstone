package rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.PlayerLookPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Codec
public class PlayerLookCodec implements InboundPacketCodec<PlayerLookPacket> {

    @Override
    public PlayerLookPacket decode(ByteBuf byteBuf) {
        final float yaw = byteBuf.readFloat();
        final float pitch = byteBuf.readFloat();
        final boolean onGround = byteBuf.readBoolean();

        Preconditions.checkArgument(Float.isFinite(yaw) && Float.isFinite(pitch),
                "Non-finite rotation " + yaw + ":" + pitch);

        return new PlayerLookPacket(yaw, pitch, onGround);
    }
}
