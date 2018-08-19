package rocks.cleanstone.game.command.completion;

import rocks.cleanstone.player.Player;
import rocks.cleanstone.utils.Vector;

public interface CommandCompletion {
    void completeCommandLine(int transactionId, String commandLine, Player sender, Vector lookedAtBlock);
}
