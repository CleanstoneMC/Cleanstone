package rocks.cleanstone.endpoint.minecraft.java.net.pluginchannel.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.endpoint.minecraft.java.net.pluginchannel.InboundPluginChannelMessageEvent;
import rocks.cleanstone.endpoint.minecraft.java.net.pluginchannel.OutboundPluginChannelMessageEvent;
import rocks.cleanstone.endpoint.minecraft.java.net.pluginchannel.vanilla.BrandMessage;

@Component
@Slf4j
public class BrandMessageListener {
    @EventListener
    public void onBrand(InboundPluginChannelMessageEvent<BrandMessage> brandMessageEvent) {
        log.info("Got Brand from " + brandMessageEvent.getPlayer().getFormattedName() + ":" + brandMessageEvent.getMessage().getBrand());

        BrandMessage brandMessage = new BrandMessage("cleanstone");
        OutboundPluginChannelMessageEvent<BrandMessage> pluginChannelMessageEvent = new OutboundPluginChannelMessageEvent<>(brandMessageEvent.getPlayer(), brandMessage);

        CleanstoneServer.publishEvent(pluginChannelMessageEvent);
    }
}
