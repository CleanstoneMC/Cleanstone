package rocks.cleanstone.game.command.completion;

import rocks.cleanstone.game.command.CommandSender;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CommandCompletion {
    CompletableFuture<List<String>> completeCommandLine(String commandLine, CommandSender sender);
}
