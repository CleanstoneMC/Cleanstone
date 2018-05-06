package rocks.cleanstone.net.netty.pipeline.outbound;

import java.nio.ByteBuffer;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.net.Connection;

public class EncryptionEncoder extends MessageToByteEncoder<ByteBuf> {

    private Cipher cipher;

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        Connection connection = ctx.channel().attr(AttributeKey.<Connection>valueOf("connection")).get();
        try {
            SecretKey sharedSecret = connection.getSharedSecret();
            if (cipher == null) {
                cipher = Cipher.getInstance("AES/CFB8/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, sharedSecret, new IvParameterSpec(sharedSecret.getEncoded()));
            }
            ByteBuffer outNioBuf = ByteBuffer.allocate(in.readableBytes());
            try {
                cipher.update(in.nioBuffer(), outNioBuf);
            } catch (ShortBufferException e) {
                throw new DecoderException("encryption output buffer too small", e);
            }
            outNioBuf.flip();
            out.writeBytes(outNioBuf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(in);
        }
    }
}
