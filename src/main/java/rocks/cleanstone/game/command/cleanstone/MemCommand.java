package rocks.cleanstone.game.command.cleanstone;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;

public class MemCommand extends SimpleCommand {

    public MemCommand() {
        super("mem");
    }

    @Override
    public void execute(CommandMessage message) {
        if (message.getCommandSender() instanceof Player) {
            if (!((Player) message.getCommandSender()).isOp()) {
                message.getCommandSender().sendRawMessage("No permission");
                return;
            }
        }

        int mb = 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();

        message.getCommandSender().sendRawMessage("Used Memory: " + (runtime.totalMemory() - runtime.freeMemory()) / mb + " MB");
        message.getCommandSender().sendRawMessage("Free Memory: " + runtime.freeMemory() / mb + " MB");
        message.getCommandSender().sendRawMessage("Total Memory: " + runtime.totalMemory() / mb + " MB");
        message.getCommandSender().sendRawMessage("Max Memory: " + runtime.maxMemory() / mb + " MB");
    }
}
