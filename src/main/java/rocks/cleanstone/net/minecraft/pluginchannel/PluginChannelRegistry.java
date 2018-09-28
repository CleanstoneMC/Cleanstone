package rocks.cleanstone.net.minecraft.pluginchannel;

import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PluginChannelRegistry {

    private final Map<String, PluginChannel> pluginChannels = new ConcurrentHashMap<>();

    public PluginChannelRegistry(List<? extends PluginChannel> pluginChannels) {
        pluginChannels.forEach(this::registerPluginChannel);
    }

    public void registerPluginChannel(PluginChannel pluginChannel) {
        if (pluginChannels.containsKey(pluginChannel.getName())) {
            throw new RuntimeException("PluginChanel is already registered");
        }

        pluginChannels.put(pluginChannel.getName(), pluginChannel);
    }

    public PluginChannel getPluginChannel(PluginChannel.PluginMessage pluginMessage) {
        return pluginChannels.values().stream().filter(pluginChannel -> {
            Class<?> messageClass = GenericTypeResolver.resolveTypeArgument(pluginChannel.getClass(), PluginChannel.class);

            return pluginMessage.getClass().equals(messageClass);
        }).findAny().orElse(null);
    }

    public PluginChannel getPluginChannel(String name) {
        return pluginChannels.get(name);
    }
}
