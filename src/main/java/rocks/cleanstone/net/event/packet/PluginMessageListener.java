package rocks.cleanstone.net.event.packet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PluginMessagePacket;
import rocks.cleanstone.net.minecraft.pluginchannel.InboundPluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.OutboundPluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannel;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannelRegistry;

@Component
public class PluginMessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PluginChannelRegistry pluginChannelRegistry;

    public PluginMessageListener(PluginChannelRegistry pluginChannelRegistry) {
        this.pluginChannelRegistry = pluginChannelRegistry;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<PluginMessagePacket> pluginMessagePacketPlayerInboundPacketEvent) {
        PluginMessagePacket packet = pluginMessagePacketPlayerInboundPacketEvent.getPacket();

        PluginChannel pluginChannel = pluginChannelRegistry.getPluginChannel(packet.getChannel());

        if (pluginChannel == null) {
            logger.warn("Cant find PluginChannel for Channel with name \"" + packet.getChannel() + "\"");
            return;
        }

        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(packet.getData());

        InboundPluginChannelMessageEvent inboundPluginChannelMessageEvent;
        try {
            //noinspection unchecked
            inboundPluginChannelMessageEvent = new InboundPluginChannelMessageEvent(
                    pluginMessagePacketPlayerInboundPacketEvent.getNetworking(),
                    pluginMessagePacketPlayerInboundPacketEvent.getConnection(),
                    pluginMessagePacketPlayerInboundPacketEvent.getPlayer(),
                    pluginChannel.getName(),
                    pluginChannel.decode(buffer)
            );
        } catch (IOException e) {
            logger.error("Error while decoding PluginChannelMessage", e);
            return;
        }
        buffer.release();

        CleanstoneServer.publishEvent(inboundPluginChannelMessageEvent);
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPluginMessage(OutboundPluginChannelMessageEvent outboundPluginChannelMessageEvent) {
        PluginChannel.PluginMessage message = outboundPluginChannelMessageEvent.getMessage();
        PluginChannel pluginChannel = pluginChannelRegistry.getPluginChannel(message);

        if (pluginChannel == null) {
            logger.error("PluginChannel does not exist");
            return;
        }

        ByteBuf buffer = Unpooled.buffer();
        try {
            //noinspection unchecked
            buffer = pluginChannel.encode(buffer, message);
        } catch (IOException e) {
            logger.error("Error while encoding PluginChannelMessage", e);
            return;
        }

        byte[] bytes = new byte[buffer.readableBytes()];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer.readByte();
        }

        PluginMessagePacket pluginMessagePacket = new PluginMessagePacket(pluginChannel.getName(), bytes);

        outboundPluginChannelMessageEvent.getPlayer().sendPacket(pluginMessagePacket);
    }
}
