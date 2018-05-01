package rocks.cleanstone.net.netty.pipeline.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.PacketCodec;
import rocks.cleanstone.net.packet.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PacketEncoder extends MessageToMessageEncoder<Packet> {

    private final Protocol protocol;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PacketEncoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet in, List<Object> out) throws Exception {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        logger.info("packetEncoder");

        int packetID = protocol.translateOutboundPacketID(in.getType().getTypeID(), connection);
        logger.info("1");
        PacketCodec codec = protocol.getPacketCodec(in.getClass(), connection
                .getClientProtocolLayer());
        logger.info("1.2");
        ByteBuf data = codec.encode(ctx.alloc().buffer(), in);
        logger.info("2");
        ByteBufUtils.writeVarInt(data, packetID);
        logger.info("3");
        if (connection.isCompressionEnabled())
            ctx.channel().attr(AttributeKey.<Integer>valueOf("uncompressedPacketLength"))
                    .set(data.readableBytes());
        logger.info("post packetEncoder");
        out.add(data);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}