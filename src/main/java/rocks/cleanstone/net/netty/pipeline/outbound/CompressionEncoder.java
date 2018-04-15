package rocks.cleanstone.net.netty.pipeline.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class CompressionEncoder extends JdkZlibEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf uncompressed, ByteBuf out) throws Exception {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        if (connection.isCompressionEnabled()) {
            ctx.channel().attr(AttributeKey.<Integer>valueOf("outUncompressedPacketLength"))
                    .set(uncompressed.readableBytes());

            // packet id is inside compressed data
            int packetId = ctx.channel().attr(AttributeKey.<Integer>valueOf("outPacketId")).get();
            ByteBufUtils.writeVarInt(out, packetId);

            super.encode(ctx, uncompressed, out);
        } else out.writeBytes(uncompressed);
    }
}
