package rocks.cleanstone.net.netty.pipeline.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.DecoderException;
import io.netty.util.AttributeKey;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.event.OutboundPacketEvent;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;

public class OutboundPacketHandler extends ChannelOutboundHandlerAdapter {

    private final Networking networking;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public OutboundPacketHandler(Networking networking) {
        this.networking = networking;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        Packet packet = (Packet) msg;
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

        if (packet.getType().getDirection() == PacketDirection.INBOUND) {
            throw new DecoderException("Sent packet has invalid direction");
        }
        if (CleanstoneServer.publishEvent(
                new OutboundPacketEvent(packet, connection, networking)).isCancelled()) return;
        logger.info("Sending " + packet.getType() + " packet to " + connection.getAddress().getHostAddress());
        ctx.write(packet, promise);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}