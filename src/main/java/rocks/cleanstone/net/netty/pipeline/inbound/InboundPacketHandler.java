package rocks.cleanstone.net.netty.pipeline.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DecoderException;
import io.netty.util.AttributeKey;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;

public class InboundPacketHandler extends ChannelInboundHandlerAdapter {

    private final Networking networking;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public InboundPacketHandler(Networking networking) {
        this.networking = networking;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Packet packet = (Packet) msg;
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        if (packet.getType().getDirection() == PacketDirection.OUTBOUND) {
            throw new DecoderException("Received packet has invalid direction");
        }
        logger.trace("Received packet " + packet.getType() + " from " + connection.getAddress().getHostAddress());
        CleanstoneServer.publishEvent(new InboundPacketEvent(packet, connection, networking));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Error occurred while handling inbound packet", cause);
        ctx.close();
    }
}