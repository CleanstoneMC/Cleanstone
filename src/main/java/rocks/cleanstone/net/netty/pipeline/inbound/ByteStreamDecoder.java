package rocks.cleanstone.net.netty.pipeline.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.net.utils.NotEnoughReadableBytesException;

@Slf4j
public class ByteStreamDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IOException {
        final Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        in.markReaderIndex();
        final int remainingPacketLength;
        try {
            remainingPacketLength = ByteBufUtils.readVarInt(in);
            if (in.readableBytes() < remainingPacketLength) {
                throw new NotEnoughReadableBytesException();
            }
        } catch (NotEnoughReadableBytesException e) {
            in.resetReaderIndex();
            return;
        }
        if (connection.isCompressionEnabled()) {
            final int uncompressedDataLength = ByteBufUtils.readVarInt(in);
            ctx.channel().attr(AttributeKey.<Integer>valueOf("inUncompressedDataLength")).set(uncompressedDataLength);
        }
        final ByteBuf newBuffer = ctx.alloc().buffer(remainingPacketLength);
        in.readBytes(newBuffer, remainingPacketLength);
        out.add(newBuffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Error occurred while framing incoming data", cause);
        ctx.close();
    }
}
