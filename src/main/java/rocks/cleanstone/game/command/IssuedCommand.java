package rocks.cleanstone.game.command;

import java.util.Collection;
import java.util.List;

public interface IssuedCommand {
    CommandSender getCommandSender();

    String getCommand();

    List<String> getParameter();
}
