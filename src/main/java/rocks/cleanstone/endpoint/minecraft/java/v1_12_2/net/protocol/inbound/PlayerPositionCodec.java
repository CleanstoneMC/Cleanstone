package rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.inbound;


import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Codec
public class PlayerPositionCodec implements InboundPacketCodec<PlayerPositionPacket> {

    @Override
    public PlayerPositionPacket decode(ByteBuf byteBuf) {
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
}
