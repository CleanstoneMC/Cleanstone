package rocks.cleanstone.net.netty.pipeline.inbound;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Set;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.protocol.Protocol;

public class IdentificationHandler extends ChannelInboundHandlerAdapter {

    private final Set<String> addressBlacklist;
    private final Protocol protocol;

    public IdentificationHandler(Protocol protocol, Set<String> addressBlacklist) {
        this.protocol = protocol;
        this.addressBlacklist = addressBlacklist;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetAddress inetaddress = socketAddress.getAddress();
        String ipAddress = inetaddress.getHostAddress();
        if (addressBlacklist.contains(ipAddress)) ctx.close();

        Connection connection = new Connection(inetaddress, ctx.channel(),
                protocol.getDefaultClientLayer(), protocol.getDefaultState());

        ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).set(connection);

        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}