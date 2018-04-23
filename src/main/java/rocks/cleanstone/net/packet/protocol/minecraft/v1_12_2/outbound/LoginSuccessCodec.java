package rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.minecraft.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftPacketCodec;
import rocks.cleanstone.net.packet.protocol.minecraft.VanillaProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class LoginSuccessCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("LoginSuccess is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        LoginSuccessPacket loginSuccessPacket = (LoginSuccessPacket) packet;

        ByteBufUtils.writeUTF8(byteBuf, loginSuccessPacket.getUUID().toString());
        ByteBufUtils.writeUTF8(byteBuf, loginSuccessPacket.getUserName());
        return byteBuf;
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }

    @Override
    public int getProtocolPacketID() {
        return 0x02;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.LOGIN;
    }
}
