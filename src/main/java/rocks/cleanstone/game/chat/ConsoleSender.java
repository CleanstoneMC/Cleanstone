package rocks.cleanstone.game.chat;

import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.CommandSender;

public interface ConsoleSender extends CommandSender {

    void run();

    void setCommandRegistry(CommandRegistry commandRegistry);
}
