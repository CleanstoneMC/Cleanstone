package rocks.cleanstone.net.netty.pipeline.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.event.ConnectionClosedEvent;
import rocks.cleanstone.net.event.ConnectionOpenEvent;
import rocks.cleanstone.net.netty.NettyConnection;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collection;

public class IdentificationHandler extends ChannelInboundHandlerAdapter {

    private final Collection<String> addressBlacklist;
    private final Networking networking;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public IdentificationHandler(Networking networking) {
        this.networking = networking;
        this.addressBlacklist = networking.getClientAddressBlacklist();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetAddress inetaddress = socketAddress.getAddress();
        String ipAddress = inetaddress.getHostAddress();
        if (addressBlacklist.contains(ipAddress)) ctx.close();

        Attribute<Connection> connectionKey = ctx.channel().attr(AttributeKey.valueOf("connection"));
        if (connectionKey.get() == null) {
            logger.info("New connection from " + ipAddress);
            Connection connection = new NettyConnection(ctx.channel(), inetaddress, networking.getProtocol()
                    .getDefaultClientLayer(), networking.getProtocol().getDefaultState());
            connectionKey.set(connection);
            if (CleanstoneServer.publishEvent(new ConnectionOpenEvent(connection, networking)).isCancelled()) {
                ctx.close();
                return;
            }
            ctx.channel().closeFuture().addListener((a) -> {
                logger.info("Connection from " + ipAddress + " closed");
                CleanstoneServer.publishEvent(new ConnectionClosedEvent(connection, networking));
            });
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.warn("Error occurred while identifying incoming data: " + cause.getMessage());
        ctx.close();
    }
}