package rocks.cleanstone.game.chat;

import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.MessageRecipient;

public interface ConsoleSender extends MessageRecipient {

    void run();

    void setCommandRegistry(CommandRegistry commandRegistry);
}
