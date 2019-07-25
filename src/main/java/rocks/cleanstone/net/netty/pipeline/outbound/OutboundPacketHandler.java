package rocks.cleanstone.net.netty.pipeline.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.DecoderException;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.event.OutboundPacketEvent;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;

@Slf4j
public class OutboundPacketHandler extends ChannelOutboundHandlerAdapter {
    private final Networking networking;

    public OutboundPacketHandler(Networking networking) {
        this.networking = networking;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        try {
            Packet packet = (Packet) msg;
            Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();

            if (packet.getType().getDirection() == PacketDirection.INBOUND) {
                throw new DecoderException("Outbound packet has invalid direction: " + packet.getType());
            }
            if (CleanstoneServer.publishEvent(
                    new OutboundPacketEvent<>(packet, connection, networking)).isCancelled()) {
                return;
            }
            log.trace("Sending " + packet.getType() + " (" + networking.getProtocol().translateOutboundPacketID(packet.getType(), connection) + ") packet to " + connection.getAddress().getHostAddress());
            ctx.write(packet, promise);
        } catch (Exception e) {
            log.error("Error occurred while handling outbound packet", e);
        }
    }
}