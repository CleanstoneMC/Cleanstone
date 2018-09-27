package rocks.cleanstone.net.minecraft.pluginchannel;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import rocks.cleanstone.core.event.CancellableEvent;

public class PluginChannelMessageEvent<T extends PluginChannel.PluginMessage> extends CancellableEvent implements ResolvableTypeProvider {

    private final String channel;
    private final T message;

    public PluginChannelMessageEvent(String channel, T message) {
        this.channel = channel;
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public T getMessage() {
        return message;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),
                ResolvableType.forInstance(message));
    }
}
