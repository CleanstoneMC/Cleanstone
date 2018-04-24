package rocks.cleanstone.net.minecraft.protocol;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.packet.protocol.ServerProtocolLayer;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public abstract class MinecraftServerProtocolLayer extends ServerProtocolLayer {

    public int getProtocolPacketID(PacketType type) {
        return ((MinecraftPacketCodec) getPacketClassCodecMap().get(type.getPacketClass()))
                .getProtocolPacketID();
    }

    public ProtocolState getProtocolState(PacketType type) {
        return ((MinecraftPacketCodec) getPacketClassCodecMap().get(type.getPacketClass()))
                .getProtocolState();
    }

    @Nullable
    public PacketType getPacketType(int protocolPacketID, ProtocolState protocolState) {
        AtomicReference<MinecraftInboundPacketType> packetType = new AtomicReference<>();

        getPacketClassCodecMap().forEach((packetClass, packetCodec) -> {
            MinecraftPacketCodec minecraftCodec = (MinecraftPacketCodec) packetCodec;
            if (minecraftCodec.getProtocolPacketID() == protocolPacketID
                    && minecraftCodec.getProtocolState() == protocolState)
                packetType.set(MinecraftInboundPacketType.byPacketClass(packetClass));
        });

        return packetType.get();
    }
}
