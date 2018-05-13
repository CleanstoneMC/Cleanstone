package rocks.cleanstone.net.netty.pipeline.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.net.utils.NotEnoughReadableBytesException;

public class ByteStreamDecoder extends ByteToMessageDecoder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        if (connection.isCompressionEnabled()) {
            int uncompressedDataLength = ByteBufUtils.readVarInt(in);
            ctx.channel().attr(AttributeKey.<Integer>valueOf("inUncompressedDataLength")).set(uncompressedDataLength);
        }
        ByteBuf newBuffer = ctx.alloc().buffer(remainingPacketLength);
        in.readBytes(newBuffer, remainingPacketLength);
        out.add(newBuffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Error occurred while framing incoming data", cause);
        ctx.close();
    }
}
