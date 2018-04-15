package rocks.cleanstone.net.netty.pipeline.inbound;

import java.nio.ByteBuffer;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.net.Connection;

public class EncryptionDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        if (!connection.isEncryptionEnabled()) {
            out.add(in);
        } else {
            try {
                SecretKey sharedSecret = connection.getSharedSecret();
                Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, sharedSecret, new IvParameterSpec(sharedSecret.getEncoded()));
                ByteBuffer outNioBuf = ByteBuffer.allocate(in.readableBytes());
                try {
                    cipher.update(in.nioBuffer(), outNioBuf);
                } catch (ShortBufferException e) {
                    throw new DecoderException("encryption output buffer too small", e);
                }
                outNioBuf.flip();
                out.add(Unpooled.wrappedBuffer(outNioBuf));
            } finally {
                ReferenceCountUtil.release(in);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
