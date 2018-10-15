package rocks.cleanstone.game.command.executor;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.command.*;

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
        final CommandSender sender = message.getCommandSender();
        if (exception instanceof NotEnoughParametersException) {
            final NotEnoughParametersException e = (NotEnoughParametersException) exception;
            sender.sendMessage("game.command.invalid-usage.not-enough-parameters",
                    e.getRequiredAmount(), e.getGivenAmount());
        } else if (exception instanceof InvalidParameterException) {
            final InvalidParameterException e = (InvalidParameterException) exception;
            final String parameterName = getParameterDisplayName(e.getRequiredParameter());
            sender.sendMessage("game.command.invalid-usage.invalid-parameter",
                    parameterName, e.getIndex() + 1, e.getGivenString());
        } else if (exception instanceof NoValidTargetException) {
            final NoValidTargetException e = (NoValidTargetException) exception;
            sender.sendMessage("game.command.invalid-usage.no-valid-target", e.getIndex() + 1);
        }
        sender.sendMessage("game.command.invalid-usage.usage", command.getUsage());
    }

    private String getParameterDisplayName(Class parameterType) {
        if (String.class.isAssignableFrom(parameterType)) {
            return CleanstoneServer.getMessage("game.command.invalid-usage.text");
        } else if (Number.class.isAssignableFrom(parameterType)) {
            return CleanstoneServer.getMessage("game.command.invalid-usage.number");
        } else {
            return parameterType.getSimpleName();
        }
    }
}
