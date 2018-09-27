package rocks.cleanstone.net.event.packet.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PluginMessagePacket;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannel;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannelRegistry;

import java.io.IOException;

@Component
public class PluginMessageListener extends PlayerInboundPacketEventListener<PluginMessagePacket> {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PluginChannelRegistry pluginChannelRegistry;

    public PluginMessageListener(PluginChannelRegistry pluginChannelRegistry) {
        this.pluginChannelRegistry = pluginChannelRegistry;
    }

    @Async(value = "playerExec")
    @EventListener
    @Override
    public void onPacket(PlayerInboundPacketEvent<PluginMessagePacket> pluginMessagePacketPlayerInboundPacketEvent) {
        PluginMessagePacket packet = pluginMessagePacketPlayerInboundPacketEvent.getPacket();

        PluginChannel pluginChannel = pluginChannelRegistry.getPluginChannel(packet.getChannel());

        if (pluginChannel == null) {
            logger.warn("Cant find PluginChannel for Channel with name \"" + packet.getChannel() + "\"");
            return;
        }

        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(packet.getData());

        PluginChannelMessageEvent pluginChannelMessageEvent;
        try {
            //noinspection unchecked
            pluginChannelMessageEvent = new PluginChannelMessageEvent(pluginChannel.getName(), pluginChannel.decode(buffer));
        } catch (IOException e) {
            logger.error("Error while decoding PluginChannelMessage", e);
            return;
        }

        CleanstoneServer.publishEvent(pluginChannelMessageEvent);
    }
}
