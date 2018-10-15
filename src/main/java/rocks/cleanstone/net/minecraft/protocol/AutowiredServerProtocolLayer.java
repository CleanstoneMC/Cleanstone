package rocks.cleanstone.net.minecraft.protocol;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.ProtocolState;

@Slf4j
public abstract class AutowiredServerProtocolLayer extends MinecraftServerProtocolLayer {

    private final List<? extends PacketCodec> packetCodecs;

    public AutowiredServerProtocolLayer(List<? extends PacketCodec> packetCodecs) {
        this.packetCodecs = packetCodecs;
    }

    @SuppressWarnings("unchecked")
    public <T extends Packet> void registerPacketCodec(Class<? extends PacketCodec<T>> codecClass, ProtocolState state,
                                                       int protocolPacketID) {

        final PacketCodec<T> codec = getCodecInstance(codecClass);

        if (codec == null) {
            log.info("Cant find PacketCodec for codecClass: " + codecClass.getName());
            return;
        }

        final Class<T> packetClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(codec.getClass(), PacketCodec.class);

        if (packetClass == null) {
            log.info("Cant find PacketClass for Codec: " + codecClass.getName());
            return;
        }

        if (!Packet.class.isAssignableFrom(packetClass)) {
            log.info("PacketClass is not is not implementing Packet");
            return;
        }

        super.registerPacketCodec(codec, packetClass, state, protocolPacketID);
    }


    @SuppressWarnings("unchecked")
    private <T> PacketCodec<T> getCodecInstance(Class<? extends PacketCodec<T>> codecClass) {
        return packetCodecs.stream().filter(o -> codecClass.isAssignableFrom(o.getClass())).findAny().orElse(null);
    }
}
