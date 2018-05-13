package rocks.cleanstone.net.netty.pipeline.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import rocks.cleanstone.net.Connection;

public class EncryptionDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Cipher cipher;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        SecretKey sharedSecret = connection.getSharedSecret();
        if (cipher == null) {
            cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, sharedSecret, new IvParameterSpec(sharedSecret.getEncoded()));
        }
        ByteBuffer outNioBuf = ByteBuffer.allocate(in.readableBytes());
        try {
            cipher.update(in.nioBuffer(), outNioBuf);
        } catch (ShortBufferException e) {
            throw new DecoderException("encryption output buffer too small", e);
        }
        outNioBuf.flip();
        out.add(Unpooled.wrappedBuffer(outNioBuf));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Error occurred while decrypting incoming data", cause);
        ctx.close();
    }
}
