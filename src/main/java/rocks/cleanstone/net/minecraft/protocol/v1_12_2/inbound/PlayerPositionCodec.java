package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;


import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Component
public class PlayerPositionCodec implements InboundPacketCodec<PlayerPositionPacket> {

    @Override
    public PlayerPositionPacket decode(ByteBuf byteBuf) {
        final double x = byteBuf.readDouble();
        final double feetY = byteBuf.readDouble();
        final double z = byteBuf.readDouble();
        final boolean onGround = byteBuf.readBoolean();

        Preconditions.checkArgument(Double.isFinite(x) && Double.isFinite(feetY) && Double.isFinite(z),
                "Non-finite position " + x + ":" + feetY + ":" + z);
        Preconditions.checkArgument(Math.abs(x) <= 3.2e7 && Math.abs(z) <= 3.2e7,
                "Too big position " + x + ":" + feetY + ":" + z + " (>3.2e7)");

        return new PlayerPositionPacket(x, feetY, z, onGround);
    }
}
