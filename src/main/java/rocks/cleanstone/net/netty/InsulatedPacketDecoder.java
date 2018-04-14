package rocks.cleanstone.net.netty;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class InsulatedPacketDecoder extends MessageToMessageDecoder<InsulatedPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, InsulatedPacket in, List<Object> out) throws Exception {

        out.add(null/*Packet*/);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}