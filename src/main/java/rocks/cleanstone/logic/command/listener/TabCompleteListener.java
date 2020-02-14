package rocks.cleanstone.logic.command.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.game.chat.event.PlayerTabCompleteEvent;
import rocks.cleanstone.game.command.completion.CommandCompletion;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TabCompleteListener {

    private final CommandCompletion commandCompletion;

    @Autowired
    public TabCompleteListener(CommandCompletion commandCompletion) {
        this.commandCompletion = commandCompletion;
    }

    @EventListener
    public void onCommand(PlayerTabCompleteEvent event) {
        CompletableFuture<List<String>> future = commandCompletion.completeCommandLine(event.getText(), event.getPlayer());

        future.thenAccept((completions) -> event.getPlayer().sendPacket(new OutTabCompletePacket(event.getInTabCompletePacket(), completions)));
    }
}
