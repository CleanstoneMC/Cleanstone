package rocks.cleanstone.net.minecraft.pluginchannel;

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

    public PluginChannel getPluginChannel(String name) {
        return pluginChannels.get(name);
    }
}
