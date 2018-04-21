package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.protocol.ServerProtocolLayer;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public abstract class MinecraftServerProtocolLayer extends ServerProtocolLayer {

    public int getProtocolPacketID(PacketType type) {
        return ((MinecraftPacketCodec) getPacketClassCodecMap().get(type.getPacketClass()))
                .getProtocolPacketID();
    }

    @Nullable
    public PacketType getPacketType(int protocolPacketID) {
        AtomicReference<MinecraftInboundPacketType> packetType = new AtomicReference<>();

        getPacketClassCodecMap().forEach((packetClass, packetCodec) -> {
            MinecraftPacketCodec minecraftCodec = (MinecraftPacketCodec) packetCodec;
            if (minecraftCodec.getProtocolPacketID() == protocolPacketID)
                packetType.set(MinecraftInboundPacketType.byPacketClass(packetClass));
        });

        return packetType.get();
    }
}
