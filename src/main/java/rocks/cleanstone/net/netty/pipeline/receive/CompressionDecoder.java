package rocks.cleanstone.net.netty.pipeline.receive;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;

public class CompressionDecoder extends JdkZlibDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        if (connection.isCompressionEnabled())
            super.decode(ctx, in, out);
        else out.add(in);
    }
}
