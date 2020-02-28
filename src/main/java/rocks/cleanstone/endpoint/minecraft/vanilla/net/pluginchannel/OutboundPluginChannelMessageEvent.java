package rocks.cleanstone.endpoint.minecraft.vanilla.net.pluginchannel;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.player.Player;

public class OutboundPluginChannelMessageEvent<T extends PluginChannel.PluginMessage> extends CancellableEvent implements ResolvableTypeProvider {

    private final Player player;
    private final T message;

    public OutboundPluginChannelMessageEvent(Player player, T message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
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
