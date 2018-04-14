package rocks.cleanstone.net.netty;

import java.io.IOException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ByteStreamDecoder extends ByteToMessageDecoder {

    private int remainingPacketLength = -1;

    private JdkZlibDecoder compressionDecoder = new JdkZlibDecoder();

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
            int packetId = ByteBufUtils.readVarInt(in);// TODO save packetId
            ByteBuf data = in.readBytes(in.readableBytes());
            out.add(data);
        } else {
            int uncompressedDataLength = ByteBufUtils.readVarInt(in);
            ByteBuf data = in.readBytes(in.readableBytes());
            out.add(data);
        }
    }
}
