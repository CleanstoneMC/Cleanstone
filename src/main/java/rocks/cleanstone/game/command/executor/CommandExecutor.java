package rocks.cleanstone.game.command.executor;

import rocks.cleanstone.game.command.CommandMessage;

public interface CommandExecutor {
    void execute(CommandMessage commandMessage);
}
