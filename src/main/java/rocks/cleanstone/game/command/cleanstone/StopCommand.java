package rocks.cleanstone.game.command.cleanstone;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class StopCommand extends SimpleCommand {

    private final PlayerManager playerManager;
    private final Executor executor;

    @Autowired
    public StopCommand(PlayerManager playerManager, Executor commandExec) {
        super("stop", String.class);
        this.playerManager = playerManager;
        this.executor = commandExec;
    }

    @Override
    public void execute(CommandMessage message) {
        if (message.getCommandSender() instanceof Player) {
            if (!((Player) message.getCommandSender()).isOp()) {
                message.getCommandSender().sendRawMessage("No permission");
                return;
            }
        }

        Text reason = message.optionalTextMessage().orElse(
                Text.ofLocalized("game.command.cleanstone.default-stop-reason",
                        CleanstoneServer.getDefaultLocale()));

        List<ListenableFuture<Void>> listenableFutures = playerManager.getOnlinePlayers().stream()
                .map(player -> player.kick(reason))
                .map(future -> JdkFutureAdapters.listenInPoolThread(future, executor))
                .collect(Collectors.toList());
        Futures.whenAllComplete(listenableFutures).run(() -> {
            CleanstoneServer.stop();
            System.exit(0);
        }, executor);
    }
}
