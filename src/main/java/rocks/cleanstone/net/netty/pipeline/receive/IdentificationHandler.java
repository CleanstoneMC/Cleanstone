package rocks.cleanstone.net.netty.pipeline.receive;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Set;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;

public class IdentificationHandler extends ChannelInboundHandlerAdapter {

    private final Set<String> addressBlacklist;

    public IdentificationHandler(Set<String> addressBlacklist) {
        this.addressBlacklist = addressBlacklist;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetAddress inetaddress = socketAddress.getAddress();
        String ipAddress = inetaddress.getHostAddress();
        if (addressBlacklist.contains(ipAddress)) ctx.close();

        Connection connection = new Connection(inetaddress);
        ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).set(connection);

        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}