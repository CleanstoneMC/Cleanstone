package rocks.cleanstone.net.netty.pipeline.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import io.netty.util.AttributeKey;

public class CompressionEncoder extends JdkZlibEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf uncompressed, ByteBuf out) throws Exception {
        ctx.channel().attr(AttributeKey.<Integer>valueOf("outUncompressedPacketLength"))
                .set(uncompressed.readableBytes());
        super.encode(ctx, uncompressed, out);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
