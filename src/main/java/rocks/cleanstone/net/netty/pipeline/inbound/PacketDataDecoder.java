package rocks.cleanstone.net.netty.pipeline.inbound;

import java.io.IOException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.netty.InsulatedPacket;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PacketDataDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf data, List<Object> out) throws IOException {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        int packetID;
        if (connection.isCompressionEnabled()) {
            packetID = ByteBufUtils.readVarInt(data);
        } else {
            packetID = ctx.channel().attr(AttributeKey.<Integer>valueOf("inPacketID")).get();
        }
        data.discardReadBytes();
        out.add(new InsulatedPacket(packetID, data));
    }
}
