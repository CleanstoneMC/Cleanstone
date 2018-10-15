package rocks.cleanstone.net.minecraft.listener;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.InPluginMessagePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.OutPluginMessagePacket;
import rocks.cleanstone.net.minecraft.pluginchannel.InboundPluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.OutboundPluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannel;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannelRegistry;

@Slf4j
@Component
public class PluginMessageListener {
    private final PluginChannelRegistry pluginChannelRegistry;

    public PluginMessageListener(PluginChannelRegistry pluginChannelRegistry) {
        this.pluginChannelRegistry = pluginChannelRegistry;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<InPluginMessagePacket> pluginMessagePacketPlayerInboundPacketEvent) {
        final InPluginMessagePacket packet = pluginMessagePacketPlayerInboundPacketEvent.getPacket();

        final PluginChannel pluginChannel = pluginChannelRegistry.getPluginChannel(packet.getChannel());

        if (pluginChannel == null) {
            log.warn("Cant find PluginChannel for Channel with name \"" + packet.getChannel() + "\"");
            return;
        }

        final ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(packet.getData());

        final InboundPluginChannelMessageEvent<?> inboundPluginChannelMessageEvent;
        try {
            inboundPluginChannelMessageEvent = new InboundPluginChannelMessageEvent<>(
                    pluginMessagePacketPlayerInboundPacketEvent.getNetworking(),
                    pluginMessagePacketPlayerInboundPacketEvent.getConnection(),
                    pluginMessagePacketPlayerInboundPacketEvent.getPlayer(),
                    pluginChannel.getName(),
                    pluginChannel.decode(buffer)
            );
        } catch (IOException e) {
            log.error("Error while decoding PluginChannelMessage", e);
            return;
        }
        buffer.release();

        CleanstoneServer.publishEvent(inboundPluginChannelMessageEvent);
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPluginMessage(OutboundPluginChannelMessageEvent outboundPluginChannelMessageEvent) {
        final PluginChannel.PluginMessage message = outboundPluginChannelMessageEvent.getMessage();
        final PluginChannel<PluginChannel.PluginMessage> pluginChannel = pluginChannelRegistry.getPluginChannel(message);

        if (pluginChannel == null) {
            log.error("PluginChannel does not exist");
            return;
        }

        ByteBuf buffer = Unpooled.buffer();
        try {
            buffer = pluginChannel.encode(buffer, message);
        } catch (IOException e) {
            log.error("Error while encoding PluginChannelMessage", e);
            return;
        }

        final byte[] bytes = new byte[buffer.readableBytes()];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer.readByte();
        }

        final OutPluginMessagePacket pluginMessagePacket = new OutPluginMessagePacket(pluginChannel.getName(), bytes);

        outboundPluginChannelMessageEvent.getPlayer().sendPacket(pluginMessagePacket);
    }
}
