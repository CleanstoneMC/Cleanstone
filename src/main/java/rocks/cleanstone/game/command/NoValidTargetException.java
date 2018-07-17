package rocks.cleanstone.game.command;

public class NoValidTargetException extends CommandException {
    private final MessageRecipient sender;
    private final int index;

    public NoValidTargetException(MessageRecipient sender, int index) {
        super("a target was neither specified, nor is " + sender.getClass().getSimpleName() + " a valid target");
        this.sender = sender;
        this.index = index;
    }

    public MessageRecipient getSender() {
        return sender;
    }

    public int getIndex() {
        return index;
    }
}
