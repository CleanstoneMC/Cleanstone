package rocks.cleanstone.net.netty.pipeline.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.net.utils.NotEnoughReadableBytesException;

import java.io.IOException;
import java.util.List;

public class ByteStreamDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IOException {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

        in.markReaderIndex();
        int remainingPacketLength;
        try {
            remainingPacketLength = ByteBufUtils.readVarInt(in);
            if (in.readableBytes() < remainingPacketLength) throw new NotEnoughReadableBytesException();
        } catch (NotEnoughReadableBytesException e) {
            in.resetReaderIndex();
            return;
        }
        if (!connection.isCompressionEnabled()) {
            out.add(in);
        } else {
            int uncompressedDataLength = ByteBufUtils.readVarInt(in);
            ctx.channel().attr(AttributeKey.<Integer>valueOf("inUncompressedDataLength")).set(uncompressedDataLength);
            out.add(in);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
