package rocks.cleanstone.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.Protocol;

import java.net.InetAddress;

public class NettyNetworking extends AbstractNetworking {

    private final boolean epoll = true;
    private final int socketBacklog = 128;
    private final boolean socketKeepAlive = true;

    public NettyNetworking(int port, InetAddress address, Protocol protocol) {
        super(port, address, protocol);
    }

    @Override
    public void start() {
        EventLoopGroup bossGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        EventLoopGroup workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer(this))
                    .option(ChannelOption.SO_BACKLOG, socketBacklog)
                    .childOption(ChannelOption.SO_KEEPALIVE, socketKeepAlive);
            try {
                bootstrap.bind().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void sendPacket(Connection connection, Packet packet) {
        connection.getChannel().writeAndFlush(packet);
    }
}
