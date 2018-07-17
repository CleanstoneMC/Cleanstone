package rocks.cleanstone.game.command.executor;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandException;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandSender;
import rocks.cleanstone.game.command.InvalidParameterException;
import rocks.cleanstone.game.command.NoValidTargetException;
import rocks.cleanstone.game.command.NotEnoughParametersException;

public class InvalidUsageExecutor implements CommandExecutor {

    private final Command command;
    private final CommandException exception;

    public InvalidUsageExecutor(Command command) {
        this.command = command;
        exception = null;
    }

    public InvalidUsageExecutor(Command command, CommandException exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute(CommandMessage message) {
        CommandSender sender = message.getCommandSender();
        if (exception instanceof NotEnoughParametersException) {
            NotEnoughParametersException e = (NotEnoughParametersException) exception;
            sender.sendMessage("game.command.invalid-usage.not-enough-parameters",
                    e.getRequiredAmount(), e.getGivenAmount());
        } else if (exception instanceof InvalidParameterException) {
            InvalidParameterException e = (InvalidParameterException) exception;
            String text = CleanstoneServer.getMessage("game.command.invalid-usage.text"),
                    number = CleanstoneServer.getMessage("game.command.invalid-usage.number");
            String parameterName = e.getRequiredParameter().getSimpleName()
                    .replace("String", text).replaceAll("Int|Double", number);
            sender.sendMessage("game.command.invalid-usage.invalid-parameter",
                    parameterName, e.getIndex() + 1, e.getGivenString());
        } else if (exception instanceof NoValidTargetException) {
            NoValidTargetException e = (NoValidTargetException) exception;
            sender.sendMessage("game.command.invalid-usage.no-valid-target", e.getIndex() + 1);
        }
        sender.sendMessage("game.command.invalid-usage.usage", command.getUsage());
    }
}
