package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.ResponsePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class ResponseCodec implements PacketCodec<ResponsePacket> {

    @Override
    public ResponsePacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("Response is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, ResponsePacket packet) throws IOException {
        ByteBufUtils.writeUTF8(byteBuf, packet.getJSONResponse());
        return byteBuf;
    }
}
