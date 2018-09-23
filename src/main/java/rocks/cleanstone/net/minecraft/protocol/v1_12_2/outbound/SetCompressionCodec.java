package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SetCompressionCodec implements PacketCodec<SetCompressionPacket> {

    @Override
    public SetCompressionPacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("SetCompression is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SetCompressionPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getThreshold());
        return byteBuf;
    }
}
