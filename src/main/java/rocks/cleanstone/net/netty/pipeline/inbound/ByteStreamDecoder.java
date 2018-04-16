package rocks.cleanstone.net.netty.pipeline.inbound;

import java.io.IOException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ByteStreamDecoder extends ByteToMessageDecoder {

    private int remainingPacketLength = -1;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        remainingPacketLength = -1;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IOException {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

        if (remainingPacketLength == -1) {
            if (in.readableBytes() < 5) return;
            remainingPacketLength = ByteBufUtils.readVarInt(in);
        }
        if (in.readableBytes() < remainingPacketLength) return;

        if (!connection.isCompressionEnabled()) {
            int packetID = ByteBufUtils.readVarInt(in);
            ctx.channel().attr(AttributeKey.<Integer>valueOf("inPacketID")).set(packetID);
            out.add(in);
        } else {
            int uncompressedDataLength = ByteBufUtils.readVarInt(in);
            out.add(in);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
