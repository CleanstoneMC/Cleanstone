package rocks.cleanstone.game.command;

public class NoValidTargetException extends RuntimeException {
    private final CommandSender sender;

    public NoValidTargetException(CommandSender sender) {
        super("a target was neither specified, nor is " + sender.getClass().getSimpleName() + " a valid target");
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }
}
