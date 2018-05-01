package rocks.cleanstone.net.netty.pipeline.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import io.netty.util.AttributeKey;

public class CompressionEncoder extends JdkZlibEncoder {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf uncompressed, ByteBuf out) throws Exception {
        try {
            ctx.channel().attr(AttributeKey.<Integer>valueOf("outUncompressedPacketLength"))
                    .set(uncompressed.readableBytes());
            super.encode(ctx, uncompressed, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
