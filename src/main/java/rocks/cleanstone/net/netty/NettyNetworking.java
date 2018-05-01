package rocks.cleanstone.net.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.packet.protocol.Protocol;

public class NettyNetworking extends AbstractNetworking {

    private final boolean epoll = false;
    private final int socketBacklog = 128;
    private final boolean socketKeepAlive = true;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private EventLoopGroup bossGroup, workerGroup;

    public NettyNetworking(int port, InetAddress address, Protocol protocol) {
        super(port, address, protocol);
    }

    public void init() {
        bossGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer(this))
                .option(ChannelOption.SO_BACKLOG, socketBacklog)
                .childOption(ChannelOption.SO_KEEPALIVE, socketKeepAlive);
        try {
            bootstrap.localAddress(this.getAddress(), this.getPort());
            bootstrap.bind().sync();
            logger.info(protocol.getClass().getSimpleName() + " bound to {}:{}", this.getAddress(), this.getPort());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
