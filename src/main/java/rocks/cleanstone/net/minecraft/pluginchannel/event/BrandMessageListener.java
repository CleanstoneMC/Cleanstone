package rocks.cleanstone.net.minecraft.pluginchannel.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannelMessageEvent;
import rocks.cleanstone.net.minecraft.pluginchannel.vanilla.BrandMessage;

@Component
public class BrandMessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onBrand(PluginChannelMessageEvent<BrandMessage> brandMessageEvent) {
        logger.info("Got Brand from Client: " + brandMessageEvent.getMessage().getBrand());
    }
}
