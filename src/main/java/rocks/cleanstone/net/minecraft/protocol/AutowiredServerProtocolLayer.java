package rocks.cleanstone.net.minecraft.protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.ProtocolState;

import java.util.List;

public abstract class AutowiredServerProtocolLayer extends MinecraftServerProtocolLayer {

    private final List<? extends PacketCodec> packetCodecs;
    Logger logger = LoggerFactory.getLogger(getClass());

    public AutowiredServerProtocolLayer(List<? extends PacketCodec> packetCodecs) {
        this.packetCodecs = packetCodecs;
    }

    @SuppressWarnings("unchecked")
    public <T extends Packet> void registerPacketCodec(Class<? extends PacketCodec<T>> codecClass, ProtocolState state,
                                                       int protocolPacketID) {

        PacketCodec<T> codec = getCodecInstance(codecClass);

        if (codec == null) {
            logger.info("Cant find PacketCodec for codecClass: " + codecClass.getName());
            return;
        }

        Class<T> packetClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(codec.getClass(), PacketCodec.class);

        if (packetClass == null) {
            logger.info("Cant find PacketClass for Codec: " + codecClass.getName());
            return;
        }

        if (!Packet.class.isAssignableFrom(packetClass)) {
            logger.info("PacketClass is not is not implementing Packet");
            return;
        }

        super.registerPacketCodec(codec, packetClass, state, protocolPacketID);
    }


    @SuppressWarnings("unchecked")
    private <T> PacketCodec<T> getCodecInstance(Class<? extends PacketCodec<T>> codecClass) {
        return packetCodecs.stream().filter(o -> codecClass.isAssignableFrom(o.getClass())).findAny().orElse(null);
    }
}
