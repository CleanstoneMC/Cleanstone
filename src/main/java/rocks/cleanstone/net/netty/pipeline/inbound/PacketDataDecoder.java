package rocks.cleanstone.net.netty.pipeline.inbound;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.protocol.PacketCodec;
import rocks.cleanstone.net.packet.protocol.Protocol;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.net.utils.NotEnoughReadableBytesException;

public class PacketDataDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Protocol protocol;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PacketDataDecoder(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        try {
            logger.info("data decoder" + ReferenceCountUtil.refCnt(in));
            in.markReaderIndex();
            int packetID;
            try {
                packetID = ByteBufUtils.readVarInt(in);
            } catch (NotEnoughReadableBytesException e) {
                in.resetReaderIndex();
                return;
            }
            logger.info("1 " + packetID);
            PacketTypeRegistry packetTypeRegistry = protocol.getPacketTypeRegistry();
            Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
            logger.info("1.2");
            PacketType packetType = packetTypeRegistry.getPacketType(
                    protocol.translateInboundPacketID(packetID, connection));
            logger.info("2");
            PacketCodec codec = protocol.getPacketCodec(packetType.getPacketClass(),
                    connection.getClientProtocolLayer());
            Preconditions.checkNotNull(codec, "Cannot find codec for packetType " + packetType
                    + " and clientLayer " + connection.getClientProtocolLayer());
            Packet packet;
            try {
                packet = codec.decode(in);
            } catch (NotEnoughReadableBytesException e) {
                in.resetReaderIndex();
                return;
            }
            logger.info("post data decoder" + ReferenceCountUtil.refCnt(in));
            out.add(packet);
        } finally {
            ReferenceCountUtil.release(in);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        //ctx.close();
    }
}