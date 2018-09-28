package rocks.cleanstone.net.minecraft.pluginchannel.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.minecraft.pluginchannel.InboundPluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.OutboundPluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.vanilla.BrandMessage;

@Component
public class BrandMessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onBrand(InboundPluginChannelMessageEvent<BrandMessage> brandMessageEvent) {
        logger.info("Got Brand from " + brandMessageEvent.getPlayer().getName() + ":" + brandMessageEvent.getMessage().getBrand());

        BrandMessage brandMessage = new BrandMessage("cleanstone");
        OutboundPluginChannelMessageEvent<BrandMessage> pluginChannelMessageEvent = new OutboundPluginChannelMessageEvent<>(brandMessageEvent.getPlayer(), brandMessage);

        CleanstoneServer.publishEvent(pluginChannelMessageEvent);
    }
}
