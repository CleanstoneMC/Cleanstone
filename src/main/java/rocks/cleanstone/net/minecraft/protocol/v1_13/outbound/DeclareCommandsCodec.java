package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;


import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.DeclareCommandsPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class DeclareCommandsCodec implements OutboundPacketCodec<DeclareCommandsPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, DeclareCommandsPacket packet) throws IOException {
        // Thanks to ViaVersion for this hack
        //https://github.com/ViaVersion/ViaVersion/blob/8136eb15e1eca93af3ff9c0ea15199e03dc8443e/common/src/main/java/us/myles/ViaVersion/protocols/protocol1_13to1_12_2/Protocol1_13To1_12_2.java#L61
        ByteBufUtils.writeVarInt(byteBuf, 2); // Size

        // Write root node
        byteBuf.writeByte(0); // Mark as command
        ByteBufUtils.writeVarInt(byteBuf, 1); // 1 child
        ByteBufUtils.writeVarInt(byteBuf, 1); // Child is at 1

        // Write arg node
        byteBuf.writeByte(0x02 | 0x04 | 0x10); // Mark as command
        ByteBufUtils.writeVarInt(byteBuf, 0); // No children
        // Extra data
        ByteBufUtils.writeUTF8(byteBuf, "args"); // Arg name
        ByteBufUtils.writeUTF8(byteBuf, "brigadier:string");
        ByteBufUtils.writeVarInt(byteBuf, 2); // Greedy
        ByteBufUtils.writeUTF8(byteBuf, "minecraft:ask_server"); // Ask server

        ByteBufUtils.writeVarInt(byteBuf, 0); // Root node index

        return byteBuf;
    }
}