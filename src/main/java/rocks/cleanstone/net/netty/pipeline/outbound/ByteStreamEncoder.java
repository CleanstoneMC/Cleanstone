package rocks.cleanstone.net.netty.pipeline.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Slf4j
public class ByteStreamEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) {
        try {
            final Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
            if (!connection.isCompressionEnabled()) {
                final int packetLength = in.readableBytes();
                ByteBufUtils.writeVarInt(out, packetLength);
                out.writeBytes(in);
            } else {
                final int uncompressedPacketLength = ctx.channel().attr(
                        AttributeKey.<Integer>valueOf("uncompressedPacketLength")).get();
                final int packetLength = in.readableBytes() + ByteBufUtils.getVarIntSize(uncompressedPacketLength);
                ByteBufUtils.writeVarInt(out, packetLength);
                ByteBufUtils.writeVarInt(out, uncompressedPacketLength);
                out.writeBytes(in);
                // TODO: Length appears to be incorrect or is in wrong format
            }
        } catch (Exception e) {
            log.error("Error occurred while framing outgoing data", e);
        }
    }
}
