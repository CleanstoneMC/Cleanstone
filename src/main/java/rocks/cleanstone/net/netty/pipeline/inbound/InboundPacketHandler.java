package rocks.cleanstone.net.netty.pipeline.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DecoderException;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;

@Slf4j
public class InboundPacketHandler extends ChannelInboundHandlerAdapter {
    private final Networking networking;

    public InboundPacketHandler(Networking networking) {
        this.networking = networking;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        final Packet packet = (Packet) msg;
        final Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        if (packet.getType().getDirection() == PacketDirection.OUTBOUND) {
            throw new DecoderException("Received packet has invalid direction");
        }
        log.trace("Received packet " + packet.getType() + " from " + connection.getAddress().getHostAddress());
        CleanstoneServer.publishEvent(new InboundPacketEvent<>(packet, connection, networking));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Error occurred while handling inbound packet", cause);
        ctx.close();
    }
}